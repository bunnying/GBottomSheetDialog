package com.bunnying.library.gbottomsheetdialog

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.annotation.StyleRes
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.math.abs

abstract class GBottomSheetDialog constructor(
    context: Context,
    private val layoutHeight: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    @StyleRes style: Int = R.style.GTheme_GBottomSheetDialogTheme
) : BottomSheetDialog(context, style),
    View.OnLayoutChangeListener, DialogInterface.OnDismissListener {
    constructor(context: Context, isMatchParent: Boolean, @StyleRes style: Int = R.style.GTheme_GBottomSheetDialogTheme)
            : this(context, if(isMatchParent) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT, style)

    init {
        window?.let { w ->
            w.decorView.setBackgroundColor(Color.TRANSPARENT)
            w.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    private var parent: View? = null
    override fun setContentView(layoutResId: Int) {
        this.setContentView(View.inflate(context, layoutResId, null))
    }
    override fun setContentView(view: View) {
        val v = View.inflate(context, R.layout.dialog_layout, null)
        v.findViewById<BottomSheetLayout?>(R.id.layoutBottomSheetDialog)?.apply {
            this.addView(view, RelativeLayout.LayoutParams.MATCH_PARENT, layoutHeight)
        }
        super.setContentView(v)
        try {
            v.parent as View?
        } catch (e: Exception) {
            null
        }?.let { parent ->
            this.parent = parent
            parent.background = null
            parent.addOnLayoutChangeListener(this)
            super.setOnDismissListener(this)
        }
    }

    private var dismissListener: DialogInterface.OnDismissListener? = null
    override fun setOnDismissListener(listener: DialogInterface.OnDismissListener?) {
        this.dismissListener = listener
    }
    override fun onDismiss(dialog: DialogInterface?) {
        try {
            parent?.removeOnLayoutChangeListener(this)
        } catch (e: Exception) {
            //
        } finally {
            dismissListener?.onDismiss(dialog)
        }
    }
    override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
        this.behavior.peekHeight = abs(bottom - top)
    }
}