package com.template.kotlintemplate.network

import com.template.kotlintemplate.network.response.RepositoriesResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("https://m.facebook.com")
    fun getHtml(): Flowable<String>

    @GET("search/repositories")
    fun searchRepository(@Query("q") keyword: String): Flowable<RepositoriesResponse>
}