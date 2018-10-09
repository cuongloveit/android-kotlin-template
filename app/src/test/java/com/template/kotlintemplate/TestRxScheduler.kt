package com.template.kotlintemplate

import com.template.kotlintemplate.ultils.RxSchedulers
import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers

open class TestRxScheduler : RxSchedulers() {
  override fun <T> applySchedulers(): FlowableTransformer<T, T> {
    return FlowableTransformer {
      it.subscribeOn(Schedulers.trampoline())
      it.observeOn(Schedulers.trampoline())
    }
  }

  fun <T> applySchedulersToObservable(): ObservableTransformer<T, T> {
    return ObservableTransformer {
      it.subscribeOn(Schedulers.trampoline())
      it.observeOn(Schedulers.trampoline())
    }
  }


}