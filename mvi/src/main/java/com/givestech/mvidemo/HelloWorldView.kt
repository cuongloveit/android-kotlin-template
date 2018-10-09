package com.givestech.mvidemo

import com.givestech.mvidemo.domain.HelloWorldViewState
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface HelloWorldView : MvpView {
  /**
   * Emits button clicks as Observables
   */

  fun showFirstPage(): Observable<Boolean>

  fun getFirstList(): Observable<Unit>

  fun loadNextItems(): Observable<Unit>


  /**
   * Render the state in the UI
   */
  fun render(state: HelloWorldViewState)

}