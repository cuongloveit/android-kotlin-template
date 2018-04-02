package com.template.kotlin.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup


fun Context.inflate(layoutResource: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false) {
    LayoutInflater.from(this).inflate(layoutResource, parent, attachToRoot)
}

