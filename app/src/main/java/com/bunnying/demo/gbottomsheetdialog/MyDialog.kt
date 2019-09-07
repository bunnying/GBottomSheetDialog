package com.bunnying.demo.gbottomsheetdialog

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.View
import com.bunnying.library.gbottomsheetdialog.GBottomSheetDialog

class MyDialog(context: Context, view: View, layoutHeight: Int): GBottomSheetDialog(context, layoutHeight) {
    companion object {
        val TAG: String = MyDialog::class.java.simpleName
    }
    init {
        setContentView(view)
    }
}