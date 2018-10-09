package com.template.kotlintemplate.practice.rxjava

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.template.kotlintemplate.R
import com.template.kotlintemplate.practice.rxjava.utils.applySchedulersForSingle
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class DemoRxJavaActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_demo_rxjava)
    Observable.fromIterable(getUrls())
        .flatMap { urls ->
          Observable.just(getBitmap(urls)).subscribeOn(Schedulers.io())
        }
        .toList()
        .applySchedulersForSingle()
        .subscribe({
          onGetListBitmap(it)
        }, {
          it.printStackTrace()
        })

  }

  private fun onGetListBitmap(it: List<Bitmap>) {
    Observable.just(it)

  }

  private fun getBitmap(url: String): Bitmap {
    return Glide.with(this).load(url).asBitmap().into(100, 100).get()
  }

  private fun getUrls(): List<String> {
    return listOf("http://lassie.l.a.pic.centerblog.net/a018eb5f.jpg",
        "http://www.enciclopet.com.br/wp-content/uploads/2016/09/beauceron.jpeg"

    )
  }
}