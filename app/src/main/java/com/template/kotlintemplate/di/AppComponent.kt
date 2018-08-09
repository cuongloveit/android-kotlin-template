package com.template.kotlintemplate.di

import com.template.kotlintemplate.RetrofitDemoActivity
import com.template.kotlintemplate.login.MainActivity
import dagger.Component

@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: RetrofitDemoActivity)
}