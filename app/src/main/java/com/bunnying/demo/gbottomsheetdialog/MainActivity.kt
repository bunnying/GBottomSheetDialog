package com.bunnying.demo.gbottomsheetdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG: String = MainActivity::class.java.simpleName
        const val TAG_DIALOG = "TAG_DIALOG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton("match parent", View.OnClickListener {
            MyDialog(this,
                AppCompatTextView(this).apply {
                    this.text = "match parent"
                    this.gravity = Gravity.CENTER
                }, ViewGroup.LayoutParams.MATCH_PARENT
            ).show()
        })
        addButton("wrap content", View.OnClickListener {
            MyDialog(this,
                AppCompatTextView(this).apply {
                    this.text = "wrap content"
                    this.gravity = Gravity.CENTER
                }, ViewGroup.LayoutParams.WRAP_CONTENT
            ).show()
        })
        addButton("500px", View.OnClickListener {
            MyDialog(this,
                AppCompatTextView(this).apply {
                    this.text = "500px"
                    this.gravity = Gravity.CENTER
                }, 500
            ).show()
        })

        addButton("match parent", View.OnClickListener {
            if(supportFragmentManager.findFragmentByTag(TAG_DIALOG) == null) {
                MyDialogFragment.newInstance(ViewGroup.LayoutParams.MATCH_PARENT).show(supportFragmentManager, TAG_DIALOG)
            }
        })
        addButton("wrap content", View.OnClickListener {
            if(supportFragmentManager.findFragmentByTag(TAG_DIALOG) == null) {
                MyDialogFragment.newInstance(ViewGroup.LayoutParams.WRAP_CONTENT).show(supportFragmentManager, TAG_DIALOG)
            }
        })
        addButton("500px", View.OnClickListener {
            if(supportFragmentManager.findFragmentByTag(TAG_DIALOG) == null) {
                MyDialogFragment.newInstance(500).show(supportFragmentManager, TAG_DIALOG)
            }
        })
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
