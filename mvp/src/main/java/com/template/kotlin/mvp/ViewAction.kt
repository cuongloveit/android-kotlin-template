package com.template.kotlin.mvp

interface ViewAction<V> {
    fun call(view: V)
}
