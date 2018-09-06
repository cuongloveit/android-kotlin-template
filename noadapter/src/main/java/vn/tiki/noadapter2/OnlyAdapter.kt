package vn.tiki.noadapter2

import android.os.AsyncTask
import android.support.annotation.VisibleForTesting
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.ViewGroup
import java.lang.ref.WeakReference
import java.util.ArrayList

/**
 * Created by Giang Nguyen on 8/14/16.
 */
class OnlyAdapter private constructor(
    @field:VisibleForTesting
    internal val typeFactory: TypeFactory,
    @field:VisibleForTesting
    internal val viewHolderFactory: ViewHolderFactory,
    @field:VisibleForTesting
    internal val diffCallback: DiffCallback) : RecyclerView.Adapter<AbsViewHolder>() {
  @VisibleForTesting
  internal var onItemClickListener: OnItemClickListener? = null
  @VisibleForTesting
  internal var onItemBindListener: OnItemBindListener? = null
  private var dataVersion: Int = 0
  private var items: List<Any>? = null
  private var updateTask: AsyncTask<Void, Void, DiffUtil.DiffResult>? = null

  override fun getItemCount(): Int {
    return if (items == null) 0 else items!!.size
  }

  override fun getItemViewType(position: Int): Int {
    val item = items!![position]
    return typeFactory.typeOf(item)
  }

  override fun onBindViewHolder(holder: AbsViewHolder, position: Int) {
    val item = items!![position]

    holder.bind(item)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsViewHolder {
    val holder = viewHolderFactory.viewHolderForType(parent, viewType)
    holder.setOnItemClickListener(onItemClickListener)
    holder.setOnItemBindListener(onItemBindListener)
    return holder
  }

  override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
    super.onDetachedFromRecyclerView(recyclerView)
    cancelUpdateTask()
  }

  override fun onViewRecycled(holder: AbsViewHolder) {
    super.onViewRecycled(holder)
    holder.unbind()
  }

  fun setItems(newItems: List<Any>?) {
    cancelUpdateTask()
    dataVersion++
    if (items == null) {
      if (newItems == null) {
        return
      }
      items = ArrayList<Any>(newItems)
      notifyDataSetChanged()
    } else if (newItems == null) {
      val oldSize = items!!.size
      items = null
      notifyItemRangeRemoved(0, oldSize)
    } else {
      val oldItems = items
      val maxItemCount = if (newItems.size > oldItems!!.size) newItems.size else oldItems.size
      if (maxItemCount < 50) {
        // we don't need to calculate in background for less than 100 items.
        val diffResult = calculateDiff(oldItems, newItems, diffCallback)
        postUpdate(newItems, diffResult)
      } else {
        updateTask = UpdateTask(oldItems, newItems, diffCallback, this).execute()
      }
    }
  }

  private fun cancelUpdateTask() {
    if (updateTask != null) {
      updateTask!!.cancel(true)
    }
  }

  private fun postUpdate(newItems: List<*>, diffResult: DiffUtil.DiffResult) {
    items = ArrayList<Any>(newItems)
    diffResult.dispatchUpdatesTo(this@OnlyAdapter)
  }

  private fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
    this.onItemClickListener = onItemClickListener
  }

  private fun setOnItemBindListener(onItemBindListener: OnItemBindListener) {
    this.onItemBindListener = onItemBindListener
  }

  class Builder {

    private var diffCallback: DiffCallback? = null
    private var onItemClickListener: OnItemClickListener? = null
    private var onItemBindListener: OnItemBindListener? = null
    private var typeFactory: TypeFactory? = null
    private var viewHolderFactory: ViewHolderFactory? = null

    fun build(): OnlyAdapter {
      if (viewHolderFactory == null) {
        throw NullPointerException("Null viewHolderFactory")
      }
      if (typeFactory == null) {
        typeFactory = DefaultTypeFactory()
      }
      if (diffCallback == null) {
        diffCallback = DefaultDiffCallback()
      }
      val adapter = OnlyAdapter(typeFactory!!, viewHolderFactory!!, diffCallback!!)
      if (onItemClickListener != null) {
        adapter.setOnItemClickListener(onItemClickListener!!)
      }
      if (onItemBindListener != null) {
        adapter.setOnItemBindListener(onItemBindListener!!)
      }
      return adapter
    }

    fun diffCallback(diffCallback: DiffCallback): Builder {
      this.diffCallback = diffCallback
      return this
    }

    fun onItemClickListener(onItemClickListener: OnItemClickListener): Builder {
      this.onItemClickListener = onItemClickListener
      return this
    }

    fun onItemBindListener(onItemBindListener: OnItemBindListener): Builder {
      this.onItemBindListener = onItemBindListener
      return this
    }

    fun typeFactory(typeFactory: TypeFactory): Builder {
      this.typeFactory = typeFactory
      return this
    }

    fun viewHolderFactory(viewHolderFactory: ViewHolderFactory): Builder {
      this.viewHolderFactory = viewHolderFactory
      return this
    }
  }

  private class UpdateTask internal constructor(
      private val oldItems: List<*>,
      private val newItems: List<*>,
      private val diffCallback: DiffCallback,
      adapter: OnlyAdapter) : AsyncTask<Void, Void, DiffUtil.DiffResult>() {

    private val adapterRef: WeakReference<OnlyAdapter>
    private val dataVersion: Int

    init {
      this.adapterRef = WeakReference(adapter)
      dataVersion = adapter.dataVersion
    }

    override fun doInBackground(vararg params: Void): DiffUtil.DiffResult {
      return calculateDiff(oldItems, newItems, diffCallback)
    }

    override fun onPostExecute(diffResult: DiffUtil.DiffResult) {
      val onlyAdapter = adapterRef.get()
      if (onlyAdapter == null || onlyAdapter.dataVersion != dataVersion) {
        return
      }
      onlyAdapter.postUpdate(newItems, diffResult)
    }
  }

  companion object {

    fun builder(): Builder {
      return Builder()
    }

    private fun calculateDiff(
        oldItems: List<*>,
        update: List<*>,
        diffCallback: DiffCallback): DiffUtil.DiffResult {
      return DiffUtil.calculateDiff(DiffUtilCallback(oldItems, update, diffCallback))
    }
  }
}
