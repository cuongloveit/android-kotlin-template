package vn.tiki.noadapter2

import android.support.v7.util.DiffUtil

/**
 * Created by Giang Nguyen on 12/15/16.
 */

internal class DiffUtilCallback(private val oldItems: List<*>?,
                                private val newItems: List<*>?,
                                private val diffCallback: DiffCallback) : DiffUtil.Callback() {

  override fun getOldListSize(): Int {
    return oldItems?.size ?: 0
  }

  override fun getNewListSize(): Int {
    return newItems?.size ?: 0
  }

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    val oldItem = getOldItem(oldItemPosition)
    val newItem = getNewItem(newItemPosition)
    return if (oldItem == null) {
      newItem == null
    } else {
      newItem != null && diffCallback.areItemsTheSame(oldItem, newItem)
    }
  }

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    val oldItem = getOldItem(oldItemPosition)
    val newItem = getNewItem(newItemPosition)
    return if (oldItem == null) {
      newItem == null
    } else {
      newItem != null && diffCallback.areContentsTheSame(oldItem, newItem)
    }
  }

  private fun getNewItem(newItemPosition: Int): Any? {
    return if (newItemPosition >= newItems!!.size) null else newItems[newItemPosition]
  }

  private fun getOldItem(oldItemPosition: Int): Any? {
    return if (oldItemPosition >= oldItems!!.size) null else oldItems[oldItemPosition]
  }

  override fun hashCode(): Int {
    return diffCallback.hashCode()
  }

  override fun equals(o: Any?): Boolean {
    if (this === o) {
      return true
    }
    if (o == null || javaClass != o.javaClass) {
      return false
    }

    val that = o as DiffUtilCallback?

    return diffCallback == that!!.diffCallback
  }
}
