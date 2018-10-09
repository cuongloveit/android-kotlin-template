package com.givestech.submodule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_demo_submodule.recyclerView
import vn.tiki.noadapter2.OnlyAdapter
import vn.tiki.noadapter2.ViewHolderFactory

class DemoSubmoduleActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_demo_submodule)

    recyclerView.layoutManager = LinearLayoutManager(this)
    val adapter = OnlyAdapter.builder()
        .viewHolderFactory(ViewHolderFactory { parent, _ -> RepositoryViewHolder.create(parent) })
        .build()
    recyclerView.adapter = adapter


  }
}