package com.template.kotlin.utils

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Toast

fun Activity.setFullScreen() {
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

fun Activity.showToolbar() {
    actionBar?.show()
}

fun Activity.hideToolbar() {
    actionBar?.hide()
}

fun AppCompatActivity.showToolbar() {
    supportActionBar?.show()
}

fun AppCompatActivity.hideToolbar() {
    supportActionBar?.hide()
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}