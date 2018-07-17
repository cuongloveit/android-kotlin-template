package com.givestech.rxfile

import android.content.Context
import android.os.Environment

object SharePreferencesHelper {
    private val APP_NAME: String = "rxfile"

    fun setPhotoPath(context: Context, pattern: String) {
        val sharedPref = context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("photoPath", pattern)
            apply()
        }
    }

    fun getPhotoPath(activity: Context): String {
        val sharedPref = activity.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString("photoPath", Environment.getDataDirectory().absolutePath)
    }

}