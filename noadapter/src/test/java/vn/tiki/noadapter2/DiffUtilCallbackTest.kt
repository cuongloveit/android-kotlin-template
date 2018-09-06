package vn.tiki.noadapter2

import java.util.Arrays
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

import junit.framework.Assert.assertEquals

/**
 * Created by Giang Nguyen on 12/17/16.
 */
class DiffUtilCallbackTest {

  @Mock
  private val diffCallback: DiffCallback? = null
  private var diffUtilCallback: DiffUtilCallback? = null

  @Before
  @Throws(Exception::class)
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    val oldItems = Arrays.asList(
        "1",
        "2",
        "3")
    val newItems = Arrays.asList(
        "1",
        "2",
        "4",
        "5"
    )
    diffUtilCallback = DiffUtilCallback(
        oldItems,
        newItems,
        diffCallback!!)
  }

  @Test
  @Throws(Exception::class)
  fun shouldReturnOldSize() {
    assertEquals(3, diffUtilCallback!!.oldListSize)
  }

  @Test
  @Throws(Exception::class)
  fun shouldReturnNewSize() {
    assertEquals(4, diffUtilCallback!!.newListSize)
  }

  @Test
  @Throws(Exception::class)
  fun shouldDelegateAreItemsTheSame() {
    diffUtilCallback!!.areItemsTheSame(2, 2)
    Mockito.verify<DiffCallback>(diffCallback).areItemsTheSame(Mockito.eq("3"), Mockito.eq("4"))
  }

  @Test
  @Throws(Exception::class)
  fun shouldDelegateAreContentsTheSame() {
    diffUtilCallback!!.areContentsTheSame(2, 2)
    Mockito.verify<DiffCallback>(diffCallback).areContentsTheSame(Mockito.eq("3"), Mockito.eq("4"))
  }
}