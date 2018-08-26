package com.template.kotlintemplate.ui.screen.repository.list

import com.template.kotlin.mvp.BasePresenter
import com.template.kotlintemplate.network.ApiService
import com.template.kotlintemplate.ultils.RxSchedulers
import javax.inject.Inject

class RepositoriesPresenter @Inject constructor(private val apiService: ApiService,
                                                private val rxSchedulers: RxSchedulers) :
    BasePresenter<RepositoriesView>() {
  override fun attach(view: RepositoriesView) {
    super.attach(view)
    getListRepository("Android")

  }

  fun getListRepository(keyword: String) {
    view?.showLoading()
    apiService.searchRepository(keyword)
        .compose(rxSchedulers.applySchedulers())
        .subscribe({
          view?.onGetRepositoriesSuccess(it.items)
        }, {
          it.printStackTrace()
          view?.onError()
        })
  }
}