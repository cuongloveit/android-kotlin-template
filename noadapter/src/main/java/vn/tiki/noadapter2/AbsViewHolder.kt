package vn.tiki.noadapter2

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Giang Nguyen on 8/14/16.
 */
open class AbsViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

  private var item: Any? = null
  private var onItemClickListener: OnItemClickListener? = null
  private var onItemBindListener: OnItemBindListener? = null

  init {
    registerOnClickOn(view)
  }

  /**
   * Called to update view
   *
   * @param item data
   */
  open fun bind(item: Any) {
    this.item = item
    if (onItemBindListener != null) {
      onItemBindListener!!.onItemBind(itemView, item, adapterPosition)
    }
  }

  /**
   * Called when a view created by adapter has been recycled. This may be
   * a good place to release expensive data or resources.
   */
  fun unbind() {
    // NoOp
  }

  protected fun registerOnClickOn(target: View) {
    target.setOnClickListener(this)
  }

  fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
    this.onItemClickListener = onItemClickListener
  }

  fun setOnItemBindListener(onItemBindListener: OnItemBindListener?) {
    this.onItemBindListener = onItemBindListener
  }

  override fun onClick(view: View) {
    val position = adapterPosition
    if ( position != RecyclerView.NO_POSITION) {
      onItemClickListener?.onItemClick(view, item!!, position)
    }
  }

}
