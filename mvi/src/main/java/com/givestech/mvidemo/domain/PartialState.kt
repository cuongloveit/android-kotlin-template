package com.givestech.mvidemo.domain

sealed class PartialState {
  object FirstLoading : PartialState()
  data class LoadFirstData(var item: List<String>) : PartialState()
  data class LoadNextItems(var nextItems: List<String>) : PartialState()
  object LoadNewest : PartialState()

  data class Error(var e: Throwable) : PartialState()
}