package com.givestech.popupmenuhelper

import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu

class PopupMenuHelper(var view: View, var onItemClick: (menuItem: MenuItem) -> Unit, vararg resTitles: Int) {
    private var popupMenu: PopupMenu = PopupMenu(view.context, view)

    init {
        resTitles.forEach {
            popupMenu.menu.add(0, it, 0, view.context.getString(it))
        }

        popupMenu.setOnMenuItemClickListener { menuItem ->
            onItemClick.invoke(menuItem)
            true
        }
        popupMenu.show()

    }


}