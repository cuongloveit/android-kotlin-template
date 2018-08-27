package com.template.kotlintemplate

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.matcher.IntentMatchers.toPackage
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import com.template.kotlintemplate.practice.MenuScreenActivity
import org.hamcrest.Matchers.*
import org.junit.*
import org.junit.runner.*

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest {

  @get:Rule
  var mActivityRule = IntentsTestRule(MenuScreenActivity::class.java)

  @Test
  fun shouldLoginSuccess() {
    onView(withText("LoginActivity")).perform(click())
    intended(allOf(
        hasComponent(hasShortClassName("com.template.kotlintemplate.ui.screen.login.LoginActivity")),
        toPackage("com.github.kotlintemplate")))
    onView(withId(R.id.edEmail)).perform(typeText("123@gmail.com"))
    onView(withId(R.id.edPassword)).perform(typeText("123"),closeSoftKeyboard())
    onView(withId(R.id.btLogin)).perform(click())
  }
}