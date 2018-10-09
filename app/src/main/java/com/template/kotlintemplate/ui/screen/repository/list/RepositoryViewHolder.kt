package com.template.kotlintemplate.ui.screen.repository.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.givestech.data.Repository
import com.template.kotlintemplate.R
import vn.tiki.noadapter2.AbsViewHolder

class RepositoryViewHolder private constructor(view: View) : AbsViewHolder(view) {
  private val tvTitle: TextView = view.findViewById<View>(R.id.tvTitle) as TextView
  private val tvDescription: TextView = view.findViewById<View>(R.id.tvDescription) as TextView

  init {
    // Set "this" to clickListener then it'll be delegated to Adapter
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