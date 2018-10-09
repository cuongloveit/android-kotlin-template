package com.template.kotlintemplate.ui.screen.repository.list

import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.LinearLayoutManager
import com.template.kotlin.mvp.MvpActivity
import com.template.kotlintemplate.MyApp
import com.template.kotlintemplate.R
import com.givestech.data.Repository
import kotlinx.android.synthetic.main.activity_repository.recyclerView
import kotlinx.android.synthetic.main.activity_repository.statusPageView
import vn.tiki.noadapter2.DiffCallback
import vn.tiki.noadapter2.OnlyAdapter
import vn.tiki.noadapter2.ViewHolderFactory
import javax.inject.Inject

class RepositoriesActivity : MvpActivity<RepositoriesView, RepositoriesPresenter>(),
    RepositoriesView {
  @Inject
  lateinit var presenter: RepositoriesPresenter
  @VisibleForTesting
  lateinit var adapter: OnlyAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    MyApp.appComponent.inject(this)
    setContentView(R.layout.activity_repository)
    connect(presenter, this)
    setupList()
  }

  private fun setupList() {
    val lm = LinearLayoutManager(this)
    recyclerView.layoutManager = lm
    adapter = OnlyAdapter.builder()
        .viewHolderFactory(ViewHolderFactory { parent, _ ->
          RepositoryViewHolder.create(parent)
        })
        .diffCallback(object : DiffCallback {
          override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return (oldItem as Repository).id == (newItem as Repository).id
          }

          override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
          }

        })
        .build()
    recyclerView.adapter = adapter

  }


  override fun onGetRepositoriesSuccess(items: List<Repository>) {
    adapter.setItems(items)
    statusPageView.showContent()
  }

  override fun onError() {
    statusPageView.showMessage("Error")
  }

  @VisibleForTesting
  override fun showLoading() {
    statusPageView.showLoading()
  }


}