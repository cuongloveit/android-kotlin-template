package com.template.kotlin.mvp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity

abstract class MvpActivity<V : Mvp.View, P : Mvp.Presenter<V>> : AppCompatActivity() {
    internal var mvpBinding: MvpBinding<V, P>? = null
    private var presenter: P? = null

    override fun onStart() {
        super.onStart()
        if (mvpBinding != null) {
            mvpBinding !!.bind()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (presenter is ActivityResultDelegate) {
            (presenter as ActivityResultDelegate).onActivityResult(ActivityResult.create(
                    requestCode,
                    resultCode,
                    data))
        }
    }

    override fun onStop() {
        super.onStop()
        if (mvpBinding != null) {
            mvpBinding !!.unbind()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mvpBinding != null) {
            mvpBinding !!.destroy(this)
        }
    }

    /**
     * Connect Presenter to View then Presenter will attach/detach view and destroy base on lifecycle.
     * NOTE: this must be called before [Activity.onStart] method.
     *
     * @param presenter the Presenter
     * @param view      the View
     */
    fun connect(presenter: P, view: V) {
        mvpBinding = MvpBinding(presenter, view)
        this.presenter = presenter
    }
}
