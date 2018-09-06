package vn.tiki.noadapter2

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue

import android.view.View
import android.view.ViewGroup
import org.junit.Before
import org.junit.Test

/**
 * Created by Giang Nguyen on 12/17/16.
 */
class BuilderTest {

  private var builder: OnlyAdapter.Builder? = null

  @Before
  @Throws(Exception::class)
  fun setUp() {
    builder = OnlyAdapter.Builder()
  }

  @Test
  @Throws(Exception::class)
  fun shouldBuildAdapter() {
    val viewHolderFactory = object {
      override fun viewHolderForType(parent: ViewGroup, type: Int): AbsViewHolder {
        return AbsViewHolder(parent)
      }
    }
    val diffCallback = object : DiffCallback {
      override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return false
      }

      override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return false
      }
    }
    val typeFactory = object {
      override fun typeOf(item: Any): Int {
        return 0
      }
    }
    val onItemClickListener = object {
      override fun onItemClick(view: View, item: Any, position: Int) {

      }
    }
    val onItemBindListener = object : OnItemBindListener {
      override fun onItemBind(view: View, item: Any, position: Int) {

      }
    }
    val adapter = builder!!
        .typeFactory(typeFactory)
        .viewHolderFactory(viewHolderFactory)
        .diffCallback(diffCallback)
        .onItemClickListener(onItemClickListener)
        .onItemBindListener(onItemBindListener)
        .build()

    assertEquals(typeFactory, adapter.typeFactory)
    assertEquals(viewHolderFactory, adapter.viewHolderFactory)
    assertEquals(diffCallback, adapter.diffCallback)
    assertEquals(onItemClickListener, adapter.onItemClickListener)
    assertEquals(onItemBindListener, adapter.onItemBindListener)
  }

  @Test
  @Throws(Exception::class)
  fun shouldBuildAdapterWithDefaultDiffCallback() {
    val adapter = builder!!.viewHolderFactory(object {
      override fun viewHolderForType(parent: ViewGroup, type: Int): AbsViewHolder {
        return AbsViewHolder(parent)
      }
    }).build()
    assertTrue(adapter.diffCallback is DefaultDiffCallback)
  }

  @Test
  @Throws(Exception::class)
  fun shouldBuildAdapterWithDefaultTypeDeterminer() {
    val viewHolderFactory = object {
      override fun viewHolderForType(parent: ViewGroup, type: Int): AbsViewHolder {
        return AbsViewHolder(parent)
      }
    }
    val diffCallback = object : DiffCallback {
      override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return false
      }

      override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return false
      }
    }
    val adapter = builder!!
        .viewHolderFactory(viewHolderFactory)
        .diffCallback(diffCallback)
        .build()

    assertTrue(adapter.typeFactory is DefaultTypeFactory)
  }

  @Test
  @Throws(Exception::class)
  fun shouldVerifyViewHolderFactory() {
    try {
      builder!!.build()
    } catch (e: Exception) {
      assertTrue(e is NullPointerException)
      assertEquals("Null viewHolderFactory", e.message)
    }

  }

}