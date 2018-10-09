package com.template.kotlintemplate.database

import com.givestech.base.IDatabaseHelper
import com.givestech.data.Repository
import com.template.kotlintemplate.MyApp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DatabaseHelper() : IDatabaseHelper {

  override fun getAllRepository(onFinished: (repositories: List<Repository>) -> Unit, onError: () -> Unit) {
    MyApp.appDatabase.repositoryDao()
        .getAllRepository()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ items ->
          onFinished.invoke(items)
        }, {
          onError.invoke()
        })
  }
}