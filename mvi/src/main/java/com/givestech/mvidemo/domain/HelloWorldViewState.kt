package com.givestech.mvidemo.domain

sealed class HelloWorldViewState {
  object LoadingState : HelloWorldViewState()
  data class DataState(val greeting: List<String>) : HelloWorldViewState()
  data class ErrorState(val error: Throwable) : HelloWorldViewState()
}

