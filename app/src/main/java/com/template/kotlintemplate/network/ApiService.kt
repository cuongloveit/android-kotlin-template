package com.template.kotlintemplate.network

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("https://m.facebook.com")
    fun getHtml(): Flowable<String>
}