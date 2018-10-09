package com.template.kotlintemplate.ui.screen.repository.list

import com.template.kotlin.mvp.Mvp
import com.givestech.data.Repository

interface RepositoriesView : Mvp.View {
  fun onGetRepositoriesSuccess(items: List<Repository>)
  fun onError()
  fun showLoading()
}