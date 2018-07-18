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

    }

    fun showMessage(message: String) {
        tvMessage.text = message
        flMessage.visibility = View.VISIBLE
        flLoading.visibility = View.GONE
        visibility = View.VISIBLE
    }

    fun showLoading() {
        flMessage.visibility = View.GONE
        flLoading.visibility = View.VISIBLE
        visibility = View.VISIBLE
    }

    fun showContent() {
        visibility = View.GONE
    }
}