package com.template.kotlintemplate

import android.arch.persistence.room.Room
import com.template.kotlintemplate.base.BaseApp
import com.template.kotlintemplate.database.AppDatabase
import com.template.kotlintemplate.di.AppComponent
import com.template.kotlintemplate.di.DaggerAppComponent

class MyApp : BaseApp() {
  companion object {
    lateinit var appComponent: AppComponent
    lateinit var appDatabase: AppDatabase
  }


  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerAppComponent.create()
    appDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "MyDatabase")
        .build()


  }

  fun setAppComponent(component: AppComponent) {
    appComponent = component
  }

}
