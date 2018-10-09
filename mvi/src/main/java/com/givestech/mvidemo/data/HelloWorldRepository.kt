package com.givestech.mvidemo.data

import io.reactivex.Observable
import java.util.concurrent.TimeUnit.SECONDS

/**
 * In a Production app, inject your Repository into your Use Case instead.
 */
object HelloWorldRepository {

  fun loadHelloWorldText(): Observable<List<String>> = Observable.just(getRandomMessage()).delay(1, SECONDS)

  fun loadNewestList(): Observable<List<String>> = Observable.just(getNewestList()).delay(1,SECONDS)

  private fun getRandomMessage(): List<String> {
    val messages = listOf("Hello World", "Hola Mundo", "Hallo Welt", "Bonjour le monde")
    return messages
  }

  private fun getNewestList(): List<String> {
    val messages = listOf("a", "b", "c", "d")
    return messages
  }
}

