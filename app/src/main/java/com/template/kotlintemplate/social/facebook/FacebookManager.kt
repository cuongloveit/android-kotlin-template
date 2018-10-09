package com.template.kotlintemplate.social.facebook

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.share.Sharer
import com.facebook.share.Sharer.Result
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import com.template.kotlintemplate.social.BasicSocialManager
import java.util.Arrays


class FacebookManager(private var loginManager: LoginManager = LoginManager.getInstance(),
                      private var callbackManager: CallbackManager = CallbackManager.Factory.create()) :
    BasicSocialManager {

  private var context: Context? = null

  private var mFacebookLoginCallback: FacebookLoginCallback? = null
  private var onFacebookEvent: OnFacebookEvent? = null
  private var onFacebookShareEvent: OnFacebookShareEvent? = null

  private class FacebookLoginCallback(private val context: Context?, private val onFacebookEvent: OnFacebookEvent?) :
      FacebookCallback<LoginResult> {

    override fun onCancel() {
      onFacebookEvent!!.onFacebookFailed()
      Log.i(TAG, "onCancel")
    }

    override fun onError(error: FacebookException) {
      onFacebookEvent!!.onFacebookFailed()
      Log.e(TAG, error.message)
    }

    override fun onSuccess(loginResult: LoginResult) {
      val fbAccessToken = loginResult.accessToken.token
      if (context != null) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(TOKEN_FB_KEY, fbAccessToken)
      }
      val parameters = Bundle()
      parameters.putString("fields", "id,name,email,first_name,last_name")
      val request = GraphRequest.newMeRequest(loginResult.accessToken) { `object`, response ->
        if (onFacebookEvent != null) {
          val infoSocial = InfoSocial()
          infoSocial.accessToken = fbAccessToken
          infoSocial.name = `object`.optString("name")
          infoSocial.email = `object`.optString("email")
          infoSocial.firstName = `object`.optString("first_name")
          infoSocial.lastName = `object`.optString("last_name")
          infoSocial.userId = `object`.optString("id")
          onFacebookEvent.onFacebookSuccess(infoSocial)
        }
      }
      request.parameters = parameters
      request.executeAsync()
    }
  }

  interface OnFacebookEvent {

    fun onFacebookSuccess(infoSocial: InfoSocial)

    fun onFacebookFailed()
  }

  interface OnFacebookShareEvent {

    fun onShareSuccessFacebook()

    fun onShareCanceledFacebook()
  }


  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    callbackManager.onActivityResult(requestCode, resultCode, data)
  }

  override fun login(activity: AppCompatActivity) {
    LoginManager.getInstance().logOut()
    this.context = activity.applicationContext
    if (mFacebookLoginCallback == null) {
      mFacebookLoginCallback = FacebookLoginCallback(context, onFacebookEvent)
    }
    facebookLogin(activity, callbackManager, Arrays.asList(*FACEBOOK_PERMISSION), mFacebookLoginCallback!!)
  }

  override fun login(fragment: Fragment) {
    LoginManager.getInstance().logOut()
    if (mFacebookLoginCallback == null) {
      mFacebookLoginCallback = FacebookLoginCallback(context, onFacebookEvent)
    }
    this.context = fragment.context
    facebookLogin(fragment, callbackManager, Arrays.asList(*FACEBOOK_PERMISSION), mFacebookLoginCallback!!)
  }

  fun setOnFacebookEvent(onFacebookEvent: OnFacebookEvent) {
    this.onFacebookEvent = onFacebookEvent
  }

  fun setOnFacebookShareEvent(onFacebookShareEvent: OnFacebookShareEvent) {
    this.onFacebookShareEvent = onFacebookShareEvent
  }

  override fun share(url: String, fragment: Fragment) {
    val shareDialog = ShareDialog(fragment)
    setUpShareConfig(url, shareDialog)
  }

  override fun share(msg: String, activity: Activity) {
    val shareDialog = ShareDialog(activity)
    setUpShareConfig(msg, shareDialog)
  }

  private fun facebookLogin(
      fragment: Fragment,
      callbackManager: CallbackManager?,
      permission: List<String>,
      facebookCallback: FacebookCallback<LoginResult>) {
    loginManager.registerCallback(callbackManager!!, facebookCallback)
    loginManager.logInWithReadPermissions(fragment, permission)
  }

  private fun facebookLogin(
      activity: Activity,
      callbackManager: CallbackManager?,
      permission: List<String>,
      facebookCallback: FacebookCallback<LoginResult>) {
    loginManager.registerCallback(callbackManager!!, facebookCallback)
    loginManager.logInWithReadPermissions(activity, permission)
  }

  private fun setUpShareConfig(url: String, shareDialog: ShareDialog) {
    if (ShareDialog.canShow(ShareLinkContent::class.java)) {
      val shareLinkContent = ShareLinkContent.Builder().setContentUrl(Uri.parse(url)).build()
      shareDialog.registerCallback(callbackManager, object : FacebookCallback<Result> {
        override fun onCancel() {
          if (onFacebookShareEvent != null) {
            onFacebookShareEvent!!.onShareCanceledFacebook()
          }
        }

        override fun onError(error: FacebookException) {
          error.printStackTrace()
        }

        override fun onSuccess(result: Sharer.Result) {
          if (onFacebookShareEvent != null) {
            onFacebookShareEvent!!.onShareSuccessFacebook()
          }
        }
      })
      shareDialog.show(shareLinkContent)

    }
  }

  companion object {

    private val TAG = "FacebookManager"
    private val TOKEN_FB_KEY = "facebook_token"
    val FACEBOOK_PERMISSION = arrayOf("public_profile", "email", "public_profile")
  }

}
