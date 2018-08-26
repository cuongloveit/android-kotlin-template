package com.givestech.statuspageview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView

class StatusPageView : FrameLayout {
  private lateinit var tvMessage: TextView
  private lateinit var flMessage: FrameLayout
  private lateinit var flLoading: FrameLayout
  private var status = STATUS_SHOW_LOADING

  companion object {
    const val STATUS_SHOW_CONTENT = 1
    const val STATUS_SHOW_MESSAGE = 2
    const val STATUS_SHOW_LOADING = 3
  }

  constructor(context: Context?) : super(context) {
    initView()
  }

  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
    initView()
  }

  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    initView()
  }

  private fun initView() {
    visibility = View.GONE
    inflate(context, R.layout.layout_status_page_view, this)
    tvMessage = findViewById(R.id.tvMessage)
    flMessage = findViewById(R.id.flMessage)
    flLoading = findViewById(R.id.flLoading)
    setOnClickListener {
      onTry?.invoke()
    }

  }

  fun showMessage(message: String) {
    status = STATUS_SHOW_MESSAGE
    tvMessage.text = message
    flMessage.visibility = View.VISIBLE
    flLoading.visibility = View.GONE
    visibility = View.VISIBLE
  }

  fun showLoading() {
    status = STATUS_SHOW_LOADING
    flMessage.visibility = View.GONE
    flLoading.visibility = View.VISIBLE
    visibility = View.VISIBLE
  }

  fun showContent() {
    status = STATUS_SHOW_CONTENT
    visibility = View.GONE
  }

  private var onTry: (() -> Unit)? = null

  fun setTryAgainListener(text: String, onTry: () -> Unit) {
    showMessage(text)
    this.onTry = onTry
  }

  fun getStatus(): Int {
    return status
  }

  fun getMessage(): String {
    return tvMessage.text.toString()
  }
}