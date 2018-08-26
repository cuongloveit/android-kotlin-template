package com.template.kotlintemplate.practice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.template.kotlintemplate.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_keep_observable_running.btSubmit
import java.util.concurrent.TimeUnit.SECONDS

private lateinit var disposable: Disposable

class KeepObservableRunningActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_keep_observable_running)
//    resumeObservableIfPossible()
    btSubmit.setOnClickListener {
      executeObservable()
    }
  }

  // 3 - Create an observable that will run a long request...
  private fun getObservable(): Observable<Long> {
    return Observable.interval(1, 1, SECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
  }

  // 4 - Create a observer that will subscribe on it
  private fun getSubscriber(): DisposableObserver<Long> {
    return object : DisposableObserver<Long>() {
      override fun onNext(result: Long) {
        Log.i("TAG", "On Next $result")
      }

      override fun onError(e: Throwable) {
        Log.e("TAG", "On Error")
      }

      override fun onComplete() {
        Log.i("TAG", "On Complete !!")
      }
    }
  }


  // 1 - Execute stream and pass observable into a Loader
  private fun executeObservable() {
    val connectableObservable = getObservable()
        .compose(RxJavaLoader.compose<Long>(this, 10))
        .publish()
    connectableObservable.subscribeWith(getSubscriber())
    disposable = connectableObservable.connect()
  }

  // 2 - [ The MAGIC FUNC ] When Fragment is created or re-created, check if a loader is running and if an observable is available (and re-subscribe to it, without launching it)
  private fun resumeObservableIfPossible() {
    val observable = RxJavaLoader.initializeLoader(this, 10, getObservable())
    if (observable != null) {
      observable.publish()
      disposable = observable.subscribeWith(getSubscriber())
    }
  }
}