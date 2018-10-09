package com.givestech.submodule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.givestech.data.Repository
import kotlinx.android.synthetic.main.item_repository.tvDescription
import kotlinx.android.synthetic.main.item_repository.tvTitle
import vn.tiki.noadapter2.AbsViewHolder

class RepositoryViewHolder private constructor(view: View) : AbsViewHolder(view) {

  init {
    view.setOnClickListener(this)
  }

  override fun bind(item: Any) {
    super.bind(item)
    item as Repository
    tvTitle.text = item.name
    tvDescription.text = item.fullName
  }

  companion object {

    fun create(parent: ViewGroup): RepositoryViewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val view = inflater.inflate(R.layout.item_repository, parent, false)
      return RepositoryViewHolder(view)
    }
  }
}