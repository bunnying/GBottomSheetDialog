package com.bunnying.demo.gbottomsheetdialog

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment

class MyDialogFragment: DialogFragment() {
    companion object {
        fun newInstance(height: Int) = MyDialogFragment().apply {
            arguments = Bundle(1).apply {
                putInt("EXTRA_HEIGHT", height)
            }
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MyDialog(
            requireContext(),
            AppCompatTextView(requireContext()).apply {
            this.text = "dialog"
            this.gravity = Gravity.CENTER
        }, arguments?.getInt("EXTRA_HEIGHT") ?: ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}