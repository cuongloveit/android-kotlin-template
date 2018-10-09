package com.givestech.base

import com.givestech.data.Repository

interface IDatabaseHelper {

  fun getAllRepository(onFinished: (repository: List<Repository>) -> Unit, onError: () -> Unit)
}