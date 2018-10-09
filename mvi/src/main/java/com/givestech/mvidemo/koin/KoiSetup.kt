package com.givestech.mvidemo.koin

import org.koin.dsl.module.module

interface HelloRepository {
  fun giveHello(): String
}

class HelloRepositoryImpl : HelloRepository {
  override fun giveHello() = "Hello Koin"
}

val appModule = module {
  // single instance of HelloRepository
  single<HelloRepository> { HelloRepositoryImpl() }

  // Simple Presenter Factory
  scope("activity") { HelloKoinPresenter(get()) }
}