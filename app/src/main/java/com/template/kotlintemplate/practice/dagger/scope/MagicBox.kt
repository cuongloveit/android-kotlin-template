package com.template.kotlintemplate.practice.dagger.scope

import dagger.Component
import dagger.Subcomponent

@MagicScope
@Subcomponent
interface MagicBox {
  fun poke(storage: Storage)
}