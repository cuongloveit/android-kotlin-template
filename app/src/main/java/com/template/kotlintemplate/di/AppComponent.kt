package com.template.kotlintemplate.di

import com.template.kotlintemplate.login.MainActivity
import dagger.Component

@Component
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}