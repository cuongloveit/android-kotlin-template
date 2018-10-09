package com.givestech.mvidemo.koin

class HelloKoinPresenter(private val repository: HelloRepository) {

  fun sayHello(): String {
    val hello = repository.giveHello()

    return hello + " from " + this
  }

}