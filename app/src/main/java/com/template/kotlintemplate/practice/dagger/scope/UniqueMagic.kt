package com.template.kotlintemplate.practice.dagger.scope

import javax.inject.Inject

@MagicScope
class UniqueMagic @Inject constructor() {
  val count = DataHolder.staticCounter++
}