package com.template.kotlintemplate.di

import com.template.kotlintemplate.RetrofitDemoActivity
import com.template.kotlintemplate.ui.screen.login.LoginActivity
import com.template.kotlintemplate.ui.screen.repository.list.RepositoriesActivity
import com.template.kotlintemplate.ui.screen.repository.list.RepositoriesPresenter
import dagger.Component

@Component(modules = [(AppModule::class)])
interface AppComponent {
  fun inject(loginActivity: LoginActivity)
  fun inject(mainActivity: RetrofitDemoActivity)
  fun inject(repositoriesActivity: RepositoriesActivity)

  fun repositoriesPresenter(): RepositoriesPresenter
}