package com.github.kotlintemplate.login

import com.github.mvp.Mvp

interface LoginView : Mvp.View {
    fun onValidateFailed()
    fun onLoginSuccess()
    fun onLoginFail()
}