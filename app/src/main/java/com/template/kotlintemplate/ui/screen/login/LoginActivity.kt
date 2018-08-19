package com.template.kotlintemplate.ui.screen.login

import android.os.Bundle
import com.template.kotlin.utils.showToast
import com.template.kotlintemplate.MyApp
import com.template.kotlintemplate.R
import com.template.kotlintemplate.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.btLogin
import kotlinx.android.synthetic.main.activity_main.edEmail
import kotlinx.android.synthetic.main.activity_main.edPassword
import javax.inject.Inject

class LoginActivity : BaseActivity<LoginView, LoginPresenter>(), LoginView {

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyApp.appComponent.inject(this)
        connect(presenter, this)
        btLogin.setOnClickListener {
            presenter.validate(edEmail.text.toString(), edPassword.text.toString())
        }
    }


    override fun onValidateFailed() {
        showToast("Please check again")
    }

    override fun onLoginSuccess() {
        showToast("Login success")
    }

    override fun onLoginFail() {
        showToast("Login fail")
    }
}
