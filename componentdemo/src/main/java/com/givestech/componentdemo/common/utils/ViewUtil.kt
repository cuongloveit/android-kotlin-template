package com.givestech.componentdemo.common.utils

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView

@SuppressLint("RestrictedApi")
fun BottomNavigationView.removeShiftMode() {
  val menuView = this.getChildAt(0) as BottomNavigationMenuView
  try {
    val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
    shiftingMode.isAccessible = true
    shiftingMode.setBoolean(menuView, false)
    shiftingMode.isAccessible = false
    for (i in 0 until menuView.childCount) {
      val item = menuView.getChildAt(i) as BottomNavigationItemView
      item.setShiftingMode(false)
      // set once again checked value, so view will be updated
      item.setChecked(item.itemData.isChecked)
    }
  } catch (e: NoSuchFieldException) {
    e.printStackTrace()
    //Timber.tag("BottomNav").e(e, "Unable to get shift mode field")
  } catch (e: IllegalAccessException) {
    //Timber.tag("BottomNav").e(e, "Unable to change value of shift mode")
  }
}