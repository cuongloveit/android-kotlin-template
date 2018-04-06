package com.template.kotlintemplate.login

import android.os.Bundle
import android.view.View
import com.template.kotlin.utils.showToast
import com.template.kotlintemplate.R
import com.template.kotlintemplate.base.BaseActivity
import com.template.kotlintemplate.di.DaggerAppComponent
import kotlinx.android.synthetic.main.activity_main.btLogin
import kotlinx.android.synthetic.main.activity_main.edEmail
import kotlinx.android.synthetic.main.activity_main.edPassword
import javax.inject.Inject

class MainActivity : BaseActivity<LoginView, LoginPresenter>(), LoginView {
    override fun onValidateFailed() {
        showToast("Please check again")
    }

    override fun onLoginSuccess() {
        showToast("Login success")
    }

    override fun onLoginFail() {
        showToast("Login fail")
    }

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerAppComponent.create().inject(this)
        connect(presenter, this)
        btLogin.setOnClickListener(View.OnClickListener {
            presenter.validate(edEmail.text.toString(), edPassword.text.toString())
        })
    }
}
