package com.template.kotlintemplate.ultils

import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class RxSchedulers {

//  fun <T> applySchedulers(): ObservableTransformer<T, T> {
//
//    return ObservableTransformer {
//      it.subscribeOn(Schedulers.io())
//      it.observeOn(AndroidSchedulers.mainThread())
//    }
//  }


  open fun <T> applySchedulers(): FlowableTransformer<T, T> {

    return FlowableTransformer {
      it.subscribeOn(Schedulers.io())
      it.observeOn(AndroidSchedulers.mainThread())
    }
  }
}