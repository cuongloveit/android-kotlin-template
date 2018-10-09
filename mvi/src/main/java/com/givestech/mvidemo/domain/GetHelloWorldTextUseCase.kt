package com.givestech.mvidemo.domain

import com.givestech.mvidemo.data.HelloWorldRepository
import io.reactivex.Observable

/**
 * In a Production app, inject your Use Case into your Presenter instead.
 */
object GetHelloWorldTextUseCase {
  fun getFirstItems(): Observable<PartialState> {
    return HelloWorldRepository.loadHelloWorldText()
        .map<PartialState> { PartialState.LoadFirstData(it) }
        //.startWith(PartialState.FirstLoading)
        .onErrorReturn { PartialState.Error(it) }
  }

  fun loadNextItems(): Observable<PartialState> {
    return HelloWorldRepository.loadNewestList()
        .map<PartialState> { PartialState.LoadNextItems(it) }
        .onErrorReturn { PartialState.Error(it) }
  }
}

