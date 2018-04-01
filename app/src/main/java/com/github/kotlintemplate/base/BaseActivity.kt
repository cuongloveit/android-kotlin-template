package com.github.kotlintemplate.base

import com.github.mvp.Mvp
import com.github.mvp.MvpActivity

 open class  BaseActivity<V : Mvp.View,P : Mvp.Presenter<V>> : MvpActivity<V, P>() {

}