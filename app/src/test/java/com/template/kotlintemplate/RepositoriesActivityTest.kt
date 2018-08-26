package com.template.kotlintemplate

import android.support.v7.widget.RecyclerView
import com.givestech.statuspageview.StatusPageView
import com.template.kotlintemplate.di.AppComponent
import com.template.kotlintemplate.di.AppModule
import com.template.kotlintemplate.network.ApiService
import com.template.kotlintemplate.network.response.RepositoriesResponse
import com.template.kotlintemplate.ui.screen.repository.list.RepositoriesActivity
import com.template.kotlintemplate.ui.screen.repository.list.RepositoriesPresenter
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.internal.operators.flowable.FlowableError
import io.reactivex.internal.operators.flowable.FlowableJust
import io.reactivex.schedulers.Schedulers
import it.cosenonjaviste.daggermock.DaggerMock
import org.junit.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.android.controller.ActivityController
import kotlin.test.assertEquals


@RunWith(RobolectricTestRunner::class)
class RepositoriesActivityTest {
  @get:Rule
  val rule = DaggerMock.rule<AppComponent>(AppModule()) {
    set {
      (RuntimeEnvironment.application as MyApp).setAppComponent(it)
      repositoriesPresenter = it.repositoriesPresenter()
    }
  }
  lateinit var repositoriesPresenter: RepositoriesPresenter
  @Mock
  lateinit var apiService: ApiService

  private lateinit var activityController: ActivityController<RepositoriesActivity>
  private lateinit var activity: RepositoriesActivity
  private lateinit var recyclerView: RecyclerView
  private lateinit var statusPageView: StatusPageView

  @Before
  fun setUp() {
    activityController = Robolectric.buildActivity(RepositoriesActivity::class.java)
    activity = activityController.get()
  }

  @Test
  fun shouldShowListUI() {
    val response = RepositoriesResponse(4, false, listOf())
    `when`(apiService.searchRepository("Android")).thenReturn(Flowable.just(response))
    initWidget()
    assertEquals(StatusPageView.STATUS_SHOW_CONTENT, statusPageView.getStatus())

  }

  @Test
  @Throws(Exception::class)
  fun shouldShowErrorUI() {
    `when`(apiService.searchRepository("Android")).thenReturn(FlowableError<RepositoriesResponse>(FlowableJust<Throwable>(
        Exception("Error"))))
    initWidget()
    assertEquals(StatusPageView.STATUS_SHOW_MESSAGE, statusPageView.getStatus())
    assertEquals("Error", statusPageView.getMessage())

  }


  private fun initWidget() {
    activityController.setup()
    statusPageView = activity.findViewById(R.id.statusPageView)
    recyclerView = activity.findViewById(R.id.recyclerView)
  }


}