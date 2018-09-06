package vn.tiki.noadapter2

import android.view.View

interface OnItemBindListener {

  fun onItemBind(view: View, item: Any, position: Int)
}
