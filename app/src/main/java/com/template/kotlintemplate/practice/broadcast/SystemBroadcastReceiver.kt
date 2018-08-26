package com.template.kotlintemplate.practice.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class  SystemBroadcastReceiver : BroadcastReceiver() {

  override fun onReceive(context: Context, intent: Intent) {
    val log = ("Action: " + intent.action + "\n"
        + "URI: "
        + intent.toUri(Intent.URI_INTENT_SCHEME)
        + "\n")
    Log.d(TAG, log)
    Toast.makeText(context, log, Toast.LENGTH_LONG).show()

  }

  companion object {

    private val TAG = "SystemBroadcastReceiver"
  }
}