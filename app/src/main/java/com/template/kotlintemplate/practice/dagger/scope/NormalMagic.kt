package com.template.kotlintemplate.practice.dagger.scope

import javax.inject.Inject

class NormalMagic @Inject constructor() {
  val count = DataHolder.staticCounter++
}