package com.template.kotlintemplate.ui.screen.repository.list

import com.template.kotlin.mvp.BasePresenter
import com.template.kotlintemplate.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoriesPresenter @Inject constructor(val apiService: ApiService) : BasePresenter<RepositoriesView>() {
  override fun attach(view: RepositoriesView) {
    super.attach(view)
    getListRepository("Android")

  }

  fun getListRepository(keyword: String) {
    view?.showLoading()
    apiService.searchRepository(keyword)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          view?.onGetRepositoriesSuccess(it.items)
        }, {
          it.printStackTrace()
          view?.onError()
        })
  }
}