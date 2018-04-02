package com.template.kotlin.mvp

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

class MvpFragment<V : Mvp.View, P : Mvp.Presenter<V>> : Fragment() {

    internal var mvpBinding: MvpBinding<V, P>? = null
    private var presenter: P? = null

    /**
     * Connect Presenter to View then Presenter will attach/detach view and destroy base on lifecycle.
     * NOTE: this must be called before [Fragment.onViewCreated] method.
     *
     * @param presenter the Presenter
     * @param view the View
     */
    fun connect(presenter: P, view: V) {
        this.presenter = presenter
        mvpBinding = MvpBinding(presenter, view)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mvpBinding != null) {
            mvpBinding!!.bind()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (mvpBinding != null) {
            mvpBinding!!.unbind()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mvpBinding != null) {
            mvpBinding!!.destroy(this.activity!!)
        }
    }
}
