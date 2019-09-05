package com.bunnying.demo.gbottomsheetdialog

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.bunnying.library.gbottomsheetdialog.GBottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton("match parent", View.OnClickListener {
            MyBottomSheetDialog(this,
                AppCompatTextView(this).apply {
                    this.text = "match parent"
                    this.gravity = Gravity.CENTER
                }, ViewGroup.LayoutParams.MATCH_PARENT
            ).show()
        })
        addButton("wrap content", View.OnClickListener {
            MyBottomSheetDialog(this,
                AppCompatTextView(this).apply {
                    this.text = "wrap content"
                    this.gravity = Gravity.CENTER
                }, ViewGroup.LayoutParams.WRAP_CONTENT
            ).show()
        })
        addButton("500px", View.OnClickListener {
            MyBottomSheetDialog(this,
                AppCompatTextView(this).apply {
                    this.text = "500px"
                    this.gravity = Gravity.CENTER
                }, 500
            ).show()
        })
    }


    private inner class MyBottomSheetDialog(context: Context, view: View, layoutHeight: Int): GBottomSheetDialog(context, layoutHeight) {
        init {
            setContentView(view)
        }
    }

    private fun addButton(text: String?, onClickListener: View.OnClickListener? = null) {
        layoutMain?.addView(
            AppCompatButton(this).apply {
                this.text = text
                this.setOnClickListener(onClickListener)
            },
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        )
    }
}
