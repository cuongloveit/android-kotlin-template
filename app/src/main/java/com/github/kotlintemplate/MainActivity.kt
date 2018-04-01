package com.github.kotlintemplate

import android.os.Bundle
import com.github.kotlintemplate.base.BaseActivity
import com.github.mvp.Mvp

class MainActivity<V : Mvp.View, P : Mvp.Presenter<V>> : BaseActivity<V, P>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
