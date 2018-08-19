package com.template.kotlintemplate.ui.screen.repository.resumeobservable

import com.template.kotlin.mvp.Mvp
import com.template.kotlintemplate.network.response.Repository

interface RepositoriesView : Mvp.View {
  fun onGetRepositoriesSuccess(items: List<Repository>)
  fun onError()
}