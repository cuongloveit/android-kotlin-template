package com.template.kotlintemplate.practice.dagger.scope

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface SingleBox {
  fun getMagicBox(): MagicBox
}