package com.template.kotlintemplate.practice.dagger.scope

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SingleTonOne @Inject constructor() {
  val count = DataHolder.staticCounter++
}