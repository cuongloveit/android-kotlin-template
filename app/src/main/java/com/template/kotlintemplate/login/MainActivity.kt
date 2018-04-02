package com.template.kotlintemplate.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.template.kotlintemplate.R
import com.template.kotlintemplate.base.BaseActivity
import com.template.kotlintemplate.di.DaggerAppComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<LoginView, LoginPresenter>(), LoginView {
    override fun onValidateFailed() {
        Toast.makeText(this, "Please check again", Toast.LENGTH_LONG).show()
    }

    override fun onLoginSuccess() {
        Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show()
    }

    override fun onLoginFail() {
        Toast.makeText(this, "Login fail", Toast.LENGTH_LONG).show()
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
