package com.template.kotlintemplate.login

import com.template.kotlin.mvp.Mvp

interface LoginView : Mvp.View {
    fun onValidateFailed()
    fun onLoginSuccess()
    fun onLoginFail()
}