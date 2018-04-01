package com.github.mvp

import android.app.Activity
import android.content.Intent

class ActivityResult private constructor(private val requestCode: Int, private val resultCode: Int, private val intent: Intent?) {

    val isCanceled: Boolean
        get() = resultCode == Activity.RESULT_CANCELED

    val isOk: Boolean
        get() = resultCode == Activity.RESULT_OK

    fun requestCode(): Int {
        return requestCode
    }

    fun resultCode(): Int {
        return resultCode
    }

    fun intent(): Intent? {
        return intent
    }

    fun isRequestCode(v: Int): Boolean {
        return requestCode == v
    }

    companion object {

        fun create(
                requestCode: Int,
                resultCode: Int,
                intent: Intent?): ActivityResult {
            return ActivityResult(requestCode, resultCode, intent)
        }
    }
}
