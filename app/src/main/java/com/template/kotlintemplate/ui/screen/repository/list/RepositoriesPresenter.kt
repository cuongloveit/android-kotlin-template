package com.template.kotlintemplate.ui.screen.repository.list

import android.util.Log
import com.template.kotlin.mvp.BasePresenter
import com.template.kotlintemplate.MyApp
import com.template.kotlintemplate.network.ApiService
import com.givestech.data.Repository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoriesPresenter @Inject constructor(private val apiService: ApiService) :
    BasePresenter<RepositoriesView>() {
  override fun attach(view: RepositoriesView) {
    super.attach(view)
    getListRepository()

  }

  private fun getListRepository() {
    view?.showLoading()
    Flowable.merge<List<Repository>>(MyApp.appDatabase
        .repositoryDao()
        .getAllRepository(), apiService.getAllRepositories()
        .doOnNext {
          MyApp.appDatabase.repositoryDao().deleteAllRepositories()
          MyApp.appDatabase.repositoryDao().insertRepositories(it)
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          Log.d("getListRepository", it.toString())
          view?.onGetRepositoriesSuccess(it)

        }, {
          it.printStackTrace()
          view?.onError()
        })

  }

}