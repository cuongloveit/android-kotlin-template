package com.template.kotlin.mvp

import android.support.annotation.CallSuper

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class RxBasePresenter<V : Mvp.View> : BasePresenter<V>() {

    private val disposeOnDestroyDisposables = CompositeDisposable()

    @CallSuper
    override fun destroy() {
        disposeOnDestroyDisposables.clear()
        super.destroy()
    }

    protected fun disposeOnDestroy(vararg disposables: Disposable) {
        if (disposables.size == 1) {
            disposeOnDestroyDisposables.add(disposables[0])
        } else if (disposables.size > 1) {
            disposeOnDestroyDisposables.addAll(*disposables)
        }
    }
}
