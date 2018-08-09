package com.template.kotlintemplate

import com.template.kotlintemplate.base.BaseApp
import com.template.kotlintemplate.di.AppComponent
import com.template.kotlintemplate.di.DaggerAppComponent

class MyApp : BaseApp() {
    companion object {
        lateinit var appComponent: AppComponent
    }


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()

    }
}
