package com.givestech.mvidemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.givestech.mvidemo.domain.HelloWorldViewState
import com.givestech.mvidemo.koin.HelloKoinPresenter
import com.givestech.mvidemo.koin.appModule
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_demo_mvi.btLoadNewest
import kotlinx.android.synthetic.main.activity_demo_mvi.helloWorldButton
import kotlinx.android.synthetic.main.activity_demo_mvi.helloWorldTextview
import kotlinx.android.synthetic.main.activity_demo_mvi.loadingIndicator
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope

class DemoMviActivity : MviActivity<HelloWorldView, HelloWorldPresenter>(), HelloWorldView {
  val helloKoinPresenter by inject<HelloKoinPresenter>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    startKoin(this, listOf(appModule))
    setContentView(R.layout.activity_demo_mvi)
    bindScope(getOrCreateScope("activity"))
    Log.d("Hello", helloKoinPresenter.sayHello())
  }

  override fun createPresenter(): HelloWorldPresenter {
    return HelloWorldPresenter()
  }


  override fun showFirstPage(): Observable<Boolean> = Observable.just(true)


  override fun getFirstList(): Observable<Unit> {
    return helloWorldButton.clicks()
  }

  override fun loadNextItems(): Observable<Unit> = btLoadNewest.clicks()

  override fun render(state: HelloWorldViewState) {
    when (state) {
      is HelloWorldViewState.LoadingState -> renderLoadingState()
      is HelloWorldViewState.DataState -> renderDataState(state)
      is HelloWorldViewState.ErrorState -> renderErrorState(state)
    }
  }

  private fun renderLoadingState() {
    loadingIndicator.visible = true
    helloWorldTextview.visible = false
  }

  private fun renderDataState(dataState: HelloWorldViewState.DataState) {
    loadingIndicator.visible = false
    helloWorldTextview.apply {
      visible = true
      text = dataState.greeting.toString()
    }
  }

  private fun renderErrorState(errorState: HelloWorldViewState.ErrorState) {
    loadingIndicator.visible = false
    helloWorldTextview.visible = false
    Toast.makeText(this, "error ${errorState.error}", Toast.LENGTH_LONG).show()
  }


}