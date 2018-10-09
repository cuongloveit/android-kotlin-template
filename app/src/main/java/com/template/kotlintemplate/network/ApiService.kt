package com.template.kotlintemplate.network

import com.givestech.data.Repository
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {
  @GET("https://m.facebook.com")
  fun getHtml(): Flowable<String>

  @GET("https://api.github.com/repositories")
  fun getAllRepositories(): Flowable<List<Repository>>
}