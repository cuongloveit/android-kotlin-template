package com.template.kotlintemplate.social

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.template.kotlintemplate.R
import com.template.kotlintemplate.social.facebook.FacebookManager
import com.template.kotlintemplate.social.facebook.FacebookManager.OnFacebookEvent
import com.template.kotlintemplate.social.facebook.InfoSocial
import kotlinx.android.synthetic.main.activity_social_login.btFacebook

class SocialDemoActivity : AppCompatActivity(), OnFacebookEvent {


  private val facebookManager = FacebookManager()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_social_login)
    facebookManager.setOnFacebookEvent(this)
    btFacebook.setOnClickListener {
      facebookManager.login(this)
    }
  }

  override fun onFacebookSuccess(infoSocial: InfoSocial) {
    Log.d("infoSocial", infoSocial.toString())
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    facebookManager.onActivityResult(requestCode, resultCode, data)
  }

  override fun onFacebookFailed() {

  }
}