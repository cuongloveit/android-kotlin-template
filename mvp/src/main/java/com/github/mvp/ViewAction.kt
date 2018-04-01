package com.github.mvp

interface ViewAction<V> {
    fun call(view: V)
}
