package com.template.kotlintemplate

import com.givestech.data.Repository
import com.template.kotlintemplate.di.AppComponent
import com.template.kotlintemplate.di.AppModule
import com.template.kotlintemplate.network.ApiService
import com.template.kotlintemplate.ui.screen.repository.list.RepositoriesPresenter
import com.template.kotlintemplate.ui.screen.repository.list.RepositoriesView
import com.template.kotlintemplate.ultils.RxSchedulers
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.internal.operators.flowable.FlowableError
import io.reactivex.internal.operators.flowable.FlowableJust
import io.reactivex.schedulers.Schedulers
import it.cosenonjaviste.daggermock.DaggerMock
import org.junit.*
import org.mockito.*
import org.mockito.Mockito.*

class RepositoriesPresenterTest {

  @get:Rule
  val rule = DaggerMock.rule<AppComponent>(AppModule()) {
    set {
      repositoriesPresenter = it.repositoriesPresenter()
    }
  }

  private lateinit var repositoriesPresenter: RepositoriesPresenter

  @Mock
  lateinit var apiService: ApiService

  @Mock
  lateinit var view: RepositoriesView
  @Mock
  lateinit var rxSchedulers: RxSchedulers

  @Test
  fun shouldGetListSuccess() {
    `when`(rxSchedulers.applySchedulers<Any>()).thenReturn(applySchedulers())
    `when`(apiService.getAllRepositories()).thenReturn(Flowable.just(listOf()))

    repositoriesPresenter.attach(view)
    verify(view).onGetRepositoriesSuccess(listOf())
  }

  @Test
  fun shouldGetListError() {
    `when`(rxSchedulers.applySchedulers<Any>()).thenReturn(applySchedulers())
    `when`(apiService.getAllRepositories()).thenReturn(FlowableError<List<Repository>>(FlowableJust<Throwable>(
        Exception("Error"))))
    repositoriesPresenter.attach(view)
    verify(view).onError()
  }

  fun <T> applySchedulers(): FlowableTransformer<T, T> {
    return FlowableTransformer {
      it.subscribeOn(Schedulers.trampoline())
      it.observeOn(Schedulers.trampoline())
    }
  }
}