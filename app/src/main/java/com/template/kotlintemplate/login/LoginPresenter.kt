package com.template.kotlintemplate.login

import android.text.TextUtils
import com.template.kotlin.mvp.BasePresenter
import javax.inject.Inject

class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    fun validate(email: String, password: String) {
        when {
            TextUtils.isEmpty(email) -> view?.onValidateFailed()
            TextUtils.isEmpty(password) -> view?.onValidateFailed()
            else -> login(email, password)

        }
    }

    fun login(email: String, password: String) {
        var isCorrect = email.equals("123@gmail.com") && password.equals("123")
        when (isCorrect) {
            true -> view?.onLoginSuccess()
            false -> view?.onLoginFail()
        }
    }

}