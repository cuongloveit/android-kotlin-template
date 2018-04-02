package com.template.kotlin.mvp

import android.support.annotation.CallSuper
import android.support.annotation.MainThread
import java.util.*


open class BasePresenter<V : Mvp.View> : Mvp.Presenter<V> {

    private val pendingViewActions = LinkedList<ViewAction<V>>()
    /**
     * Return view. NOTE: view is nullable.
     *
     * @return View
     */
    var view: V? = null
        private set
    var isAttached: Boolean = false
        private set
    var isDetached: Boolean = false
        private set

    /**
     * Return view if view != null or throw exception otherwise.
     *
     * @return View
     */
    protected val viewOrThrow: V
        get() = this.view ?: throw IllegalStateException("view not attached")

    /**
     * Send action to view when view is attached.
     *
     * @param action to run.
     */
    @MainThread
    protected fun sendToView(action: ViewAction<V>) {
        if (view == null) {
            pendingViewActions.add(action)
        } else {
            action.call(view!!)
        }

    }

    @CallSuper
    override fun attach(view: V) {
        this.view = view
        if (pendingViewActions.isEmpty()) {
            return
        }

        do {
            val action = pendingViewActions.remove()
            action.call(view)
        } while (pendingViewActions.size > 0)
        isAttached = true
        isDetached = false
    }

    @CallSuper
    override fun detach() {
        this.view = null
        isAttached = false
        isDetached = true
    }

    override fun destroy() {

    }
}
