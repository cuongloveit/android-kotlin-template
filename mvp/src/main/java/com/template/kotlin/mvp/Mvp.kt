package com.template.kotlin.mvp


interface Mvp {

    interface View

    interface Presenter<V : View> {

        fun attach(view: V)

        fun detach()

        fun destroy()
    }
}
