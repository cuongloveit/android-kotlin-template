package com.template.kotlintemplate

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.template.kotlintemplate.network.ApiService
import com.template.kotlintemplate.network.Page
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import java.lang.reflect.Type
import javax.inject.Inject


class RetrofitDemoActivity : AppCompatActivity() {
    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(View(this))
        MyApp.appComponent.inject(this)
        val retrofit = Retrofit.Builder()
                .baseUrl("https://mobile.facebook.com/")
                .addConverterFactory(PageAdapter.FACTORY)
                .build()
        val requestAddress = retrofit.create(PageService::class.java)
        requestAddress
                .getHtml()
                .enqueue(object : Callback<Page> {
                    override fun onResponse(call: Call<Page>, response: Response<Page>) {
                        Log.i("onResponse", response.body() !!.content)
                    }

                    override fun onFailure(call: Call<Page>, t: Throwable) {

                    }
                })
    }


    internal class PageAdapter : Converter<ResponseBody, Page> {
        override fun convert(value: ResponseBody?): Page {
            return Page(value !!.string())
        }


        companion object {
            val FACTORY: Converter.Factory = object : Converter.Factory() {
                override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?,
                                                   retrofit: Retrofit?): Converter<ResponseBody, *>? {
                    return if (type === Page::class.java) PageAdapter() else null
                }


            }
        }
    }

    internal interface PageService {
        @GET("https://web.facebook.com/Banmuonhenho/videos/2357151730979902/")
        fun getHtml(): Call<Page>
    }
}

