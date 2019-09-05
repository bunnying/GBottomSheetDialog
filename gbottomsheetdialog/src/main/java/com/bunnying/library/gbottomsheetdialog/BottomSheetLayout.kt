package com.bunnying.library.gbottomsheetdialog

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.util.TypedValue

internal class BottomSheetLayout: RelativeLayout {
    private val radius: Float
    private val radii: FloatArray

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val r =  try {
            val typedValue = TypedValue()
            context.theme.resolveAttribute(android.R.attr.radius, typedValue, true)
            context.resources.getDimension(typedValue.resourceId)
        } catch (e: Exception) {
            0f
        }
        this.radius = r
        this.radii = floatArrayOf(r, r, r, r, 0f, 0f, 0f, 0f)
    }


    private val rect = RectF()
    private val path = Path()
    @SuppressLint("CanvasSize")
    @Synchronized override fun draw(canvas: Canvas?) {
        canvas?.let {
            it.clipPath(path.apply {
                this.reset()
//                this.addRoundRect(0f, 0f, it.width.toFloat(), it.height.toFloat(), radii, Path.Direction.CW)
                this.addRoundRect(rect.apply { set(0f, 0f, it.width.toFloat(), it.height.toFloat()) }, radii, Path.Direction.CW)
            })
        }
        super.draw(canvas)
    }
}