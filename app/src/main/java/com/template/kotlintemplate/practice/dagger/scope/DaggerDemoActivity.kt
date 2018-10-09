package com.template.kotlintemplate.practice.dagger.scope

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.template.kotlintemplate.R
import kotlinx.android.synthetic.main.activity_demo_dagger.button
import kotlinx.android.synthetic.main.activity_demo_dagger.tvCounter

class DaggerDemoActivity : AppCompatActivity() {
  private lateinit var magicBox: MagicBox
  private lateinit var mainBox: SingleBox
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_demo_dagger)
    mainBox = DaggerSingleBox.create()
    magicBox = mainBox.getMagicBox()
    button.setOnClickListener {
      //magicBox = mainBox.getMagicBox()//recreate MagicBox
      val storage = Storage() //create Storage
      magicBox.poke(storage)
      tvCounter.text = "Unique ${storage.uniqueMagic.count}" +
          "\nNormal ${storage.normalMagic.count} "+
          "\nSingle ${storage.singleTonOne.count} "
    }
  }


}