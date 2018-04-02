package com.github.kotlintemplate.di

import com.github.kotlintemplate.login.MainActivity
import dagger.Component

@Component
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}