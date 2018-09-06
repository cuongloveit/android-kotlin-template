package com.template.kotlintemplate.di

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.template.kotlintemplate.BuildConfig
import com.template.kotlintemplate.network.ApiService
import com.template.kotlintemplate.ultils.RxSchedulers
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
open class AppModule {
  @Provides
  open fun provideOkhttp(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.addInterceptor(LoggingInterceptor.Builder()
        .loggable(BuildConfig.DEBUG)
        .setLevel(Level.BASIC)
        .log(Platform.INFO)
        .request("Request")
        .response("Response")
        .build())

    return client.build()
  }

  @Provides
  open fun provideRetrofitRemote(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
  }

  @Provides
  open fun createApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
  }

  @Provides
  open fun provideRxScheduler(): RxSchedulers {
    return RxSchedulers()
  }

}