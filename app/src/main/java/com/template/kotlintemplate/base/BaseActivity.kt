package com.template.kotlintemplate.base

import com.template.kotlin.mvp.Mvp
import com.template.kotlin.mvp.MvpActivity

 open class  BaseActivity<V : Mvp.View,P : Mvp.Presenter<V>> : MvpActivity<V, P>() {

}