package com.template.kotlin.mvp

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View

class MvpBinding<V : Mvp.View, P : Mvp.Presenter<V>>(private val presenter: P, private val view: V) : View.OnAttachStateChangeListener {

    private fun findActivity(context: Context): Activity {
        if (context is Activity) {
            return context
        }

        return if (context is ContextWrapper) {
            findActivity(context.baseContext)
        } else {
            throw IllegalArgumentException("context or baseContext must be instance of " + Activity::class.java.name)
        }
    }

    internal fun unbind() {
        presenter.detach()
    }

    internal fun destroy(activity: Activity) {
        if (activity.isFinishing) {
            presenter.destroy()
        }
    }

    override fun onViewAttachedToWindow(view: View) {
        bind()
    }

    internal fun bind() {
        presenter.attach(view)
    }

    override fun onViewDetachedFromWindow(view: View) {
        unbind()
        destroy(findActivity(view.context))
    }
}
