package com.template.kotlintemplate.social

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity


interface BasicSocialManager {
  fun login(activity: AppCompatActivity)
  fun login(fragment: Fragment)
  fun share(msg: String, activity: Activity)
  fun share(msg: String, fragment: Fragment)
  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}
