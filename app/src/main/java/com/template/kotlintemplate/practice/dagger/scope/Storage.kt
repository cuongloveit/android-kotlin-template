package com.template.kotlintemplate.practice.dagger.scope

import javax.inject.Inject

class Storage {

  @Inject
  lateinit var singleTonOne: SingleTonOne
  @Inject
  lateinit var uniqueMagic: UniqueMagic
  @Inject
  lateinit var normalMagic: NormalMagic
}