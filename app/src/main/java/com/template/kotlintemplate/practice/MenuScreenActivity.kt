package com.template.kotlintemplate.practice

import android.os.Build
import android.os.Bundle
import android.preference.PreferenceActivity
import com.template.kotlintemplate.R
import java.util.ArrayList

/**
 * A [PreferenceActivity] that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 *
 * See [Android Design: Settings](http://developer.android.com/design/patterns/settings.html)
 * for design guidelines and the [Settings API Guide](http://developer.android.com/guide/topics/ui/settings.html)
 * for more information on developing a Settings UI.
 */
class MenuScreenActivity : AppCompatPreferenceActivity() {
  private val fragments = ArrayList<String>()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupActionBar()
  }

  /**
   * Set up the [android.app.ActionBar], if the API is available.
   */
  private fun setupActionBar() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }


  override fun onBuildHeaders(target: MutableList<Header>) {
    loadHeadersFromResource(R.xml.preferences_headers, target)
    fragments.clear()
    val headerIterator = target.iterator()
    while (headerIterator.hasNext()) {
      val header = headerIterator.next()
      if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      }

      fragments.add(header.fragment)
    }
  }


  override fun isValidFragment(fragmentName: String): Boolean = fragments.contains(fragmentName)


}
