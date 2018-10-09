package com.givestech.mvidemo

import com.givestech.mvidemo.domain.GetHelloWorldTextUseCase
import com.givestech.mvidemo.domain.HelloWorldViewState
import com.givestech.mvidemo.domain.PartialState
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class HelloWorldPresenter : MviBasePresenter<HelloWorldView, HelloWorldViewState>() {
  override fun bindIntents() {
    val firstList: Observable<PartialState> = intent(HelloWorldView::getFirstList)
        .subscribeOn(Schedulers.io())
        .switchMap { GetHelloWorldTextUseCase.getFirstItems() }
        .observeOn(AndroidSchedulers.mainThread())

    val nextItems: Observable<PartialState> = intent(HelloWorldView::loadNextItems)
        .subscribeOn(Schedulers.io())
        .switchMap { GetHelloWorldTextUseCase.loadNextItems() }
        .observeOn(AndroidSchedulers.mainThread())

    val allIntents = Observable.mergeArray(firstList, nextItems)
    val initialState = HelloWorldViewState.LoadingState
    val finalState: Observable<HelloWorldViewState> = allIntents.scan(initialState, this::viewStateReducer)

    subscribeViewState(finalState, HelloWorldView::render)
  }

  private fun viewStateReducer(oldState: HelloWorldViewState, newState: PartialState): HelloWorldViewState {
    return when (newState) {
      is PartialState.FirstLoading -> HelloWorldViewState.LoadingState
      is PartialState.LoadFirstData -> HelloWorldViewState.DataState(newState.item)
      is PartialState.LoadNextItems -> {
        if (oldState is HelloWorldViewState.DataState) {
          val items = oldState.greeting
          val finalList = ArrayList(items)
          finalList.addAll(newState.nextItems)
          HelloWorldViewState.DataState(finalList)
        } else {
          HelloWorldViewState.LoadingState
        }


      }

      else -> HelloWorldViewState.LoadingState
    }


  }
}