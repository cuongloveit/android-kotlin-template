package com.template.kotlintemplate.practice

import android.app.Activity
import android.app.LoaderManager
import android.content.Context
import android.content.Loader
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.BehaviorSubject

class RxJavaLoader<T> private constructor(context: Context, private val observable: Observable<T>) :
    Loader<T>(context) {

  private val cache = BehaviorSubject.create<T>()

  private var disposable: Disposable? = null

  private class CreateLoaderCallback<T>(private val context: Context, private val observable: Observable<T>) :
      LoaderManager.LoaderCallbacks<T> {

    override fun onCreateLoader(id: Int, args: Bundle): Loader<T> {
      return RxJavaLoader(context, observable)
    }

    override fun onLoadFinished(loader: Loader<T>, data: T) { /* nothing */
    }

    override fun onLoaderReset(loader: Loader<T>) { /* nothing */
    }
  }

  override fun onReset() {
    super.onReset()
    disposable?.dispose()
  }

  override fun onStartLoading() {
    super.onStartLoading()
    disposable = observable.subscribeWith(cache).subscribeWith(object : DisposableObserver<T>() {
      override fun onComplete() {}

      override fun onError(e: Throwable) {}

      override fun onNext(t: T) {}
    })
  }

  companion object {

    fun <T> compose(activity: Activity, id: Int): ObservableTransformer<T, T> {
      return ObservableTransformer { upstream -> create(activity, id, upstream) }
    }


    fun <T> create(activity: Activity, id: Int, observable: Observable<T>): Observable<T> {
      val loaderManager = activity.loaderManager

      val createLoaderCallback = CreateLoaderCallback(activity, observable)

      loaderManager.restartLoader(id, Bundle(), createLoaderCallback)

      val rxLoader = loaderManager.getLoader<Any>(id) as RxJavaLoader<T>
      return rxLoader.cache.hide()
    }

    fun <T> initializeLoader(activity: Activity, id: Int, observable: Observable<T>): Observable<T>? {
      val loaderManager = activity.loaderManager
      val createLoaderCallback = CreateLoaderCallback(activity, observable)

      if (loaderManager.getLoader<Any>(id) != null) {
        loaderManager.initLoader(id, null, createLoaderCallback)
      }

      if (loaderManager.getLoader<Any>(id) == null) {
        return null
      }

      val rxLoader = loaderManager.getLoader<Any>(id) as RxJavaLoader<T>
      return if (!rxLoader.cache.hasComplete()) {
        rxLoader.cache.hide()
      } else {
        null
      }
    }
  }
}