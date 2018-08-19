package com.template.kotlintemplate.ui.screen.login

import com.template.kotlin.mvp.Mvp

interface LoginView : Mvp.View {
    fun onValidateFailed()
    fun onLoginSuccess()
    fun onLoginFail()
}