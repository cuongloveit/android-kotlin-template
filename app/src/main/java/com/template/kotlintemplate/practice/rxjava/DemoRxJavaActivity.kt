package com.template.kotlintemplate.practice.rxjava

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import com.template.kotlintemplate.R
import com.template.kotlintemplate.practice.rxjava.utils.applySchedulersForSingle
import io.reactivex.Observable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


class DemoRxJavaActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
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

        val ps1 = PublishSubject.create<Boolean>()
        val ps2 = PublishSubject.create<Boolean>()
        val ps3 = PublishSubject.create<Boolean>()

        Observable.zip(ps1, ps2, ps3, Function3<Boolean, Boolean, Boolean, Boolean> { _, _, _ -> true })
                .subscribe {
                    Log.d("Adasd", "adasd")
                }

        ps3.onNext(true)
        ps1.onNext(true)
        ps1.onComplete()
        Handler().postDelayed({
            ps2.onNext(true)
            ps2.onComplete()
        }, 300)
        ps3.onComplete()


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