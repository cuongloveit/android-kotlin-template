package com.template.kotlintemplate.ui.screen.repository.resumeobservable

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.template.kotlin.mvp.MvpActivity
import com.template.kotlintemplate.MyApp
import com.template.kotlintemplate.R
import com.template.kotlintemplate.network.response.Repository
import kotlinx.android.synthetic.main.activity_repository.recyclerView
import vn.tiki.noadapter2.OnlyAdapter
import javax.inject.Inject

class RepositoriesActivity : MvpActivity<RepositoriesView, RepositoriesPresenter>(), RepositoriesView {
  @Inject
  lateinit var presenter: RepositoriesPresenter
  private lateinit var adapter: OnlyAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    MyApp.appComponent.inject(this)
    setContentView(R.layout.activity_repository)
    connect(presenter, this)
    setupList()
  }

  private fun setupList() {
    recyclerView.layoutManager = LinearLayoutManager(this)
    adapter = OnlyAdapter.builder()
        .viewHolderFactory { parent, type -> RepositoryViewHolder.create(parent) }
        .build()
    recyclerView.adapter = adapter

  }


  override fun onGetRepositoriesSuccess(items: List<Repository>) {
      adapter.setItems(items)
  }

  override fun onError() {

  }


}