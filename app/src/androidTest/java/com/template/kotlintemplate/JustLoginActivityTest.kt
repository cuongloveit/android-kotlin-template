package com.template.kotlintemplate

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import com.template.kotlintemplate.ui.screen.login.LoginActivity
import org.hamcrest.Matchers.*
import org.junit.*
import org.junit.runner.*


@RunWith(AndroidJUnit4::class)
@LargeTest
class JustLoginActivityTest {

  @get:Rule
  var mActivityRule = IntentsTestRule(LoginActivity::class.java)

  @Test
  fun shouldLoginSuccess() {
    onView(withId(R.id.edEmail)).perform(typeText("123@gmail.com"))
    onView(withId(R.id.edPassword)).perform(typeText("123"), closeSoftKeyboard())
    onView(withId(R.id.btLogin)).perform(click())
    onView(withText("Login successfully")).inRoot(withDecorView(not(`is`(mActivityRule.activity.getWindow().getDecorView())))).check(
        matches(isDisplayed()))
  }

  @Test
  fun shouldLoginFail() {
    onView(withId(R.id.edEmail)).perform(typeText("123@gmail.com"))
    onView(withId(R.id.edPassword)).perform(typeText("1234"), closeSoftKeyboard())//wrong password
    onView(withId(R.id.btLogin)).perform(click())
//    onView(withText("Login fail")).inRoot(withDecorView(not(`is`(mActivityRule.activity.getWindow().getDecorView())))).check(
//        matches(isDisplayed()))
  }
}