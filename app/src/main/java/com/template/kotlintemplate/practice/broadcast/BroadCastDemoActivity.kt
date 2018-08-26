package com.template.kotlintemplate.practice.broadcast

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.template.kotlintemplate.R
import kotlinx.android.synthetic.main.activity_broadcast_demo.button


class BroadCastDemoActivity : AppCompatActivity() {
  private lateinit var receiver: MyBroadcastReceiver
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_broadcast_demo)
    receiver = MyBroadcastReceiver()
    val intentFilter = IntentFilter("com.example.broadcast.MY_NOTIFICATION")
    registerReceiver(receiver, intentFilter)
    button.setOnClickListener {
      sendBroadcast()
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    unregisterReceiver(receiver)
  }

  private fun sendBroadcast() {
    val intent = Intent()
    intent.action = "com.example.broadcast.MY_NOTIFICATION"
    intent.putExtra("data", "Notice me!")
    sendBroadcast(intent)
  }
}