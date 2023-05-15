package com.demo.common_ui.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.demo.common_ui.R

object ProgressDialogUtil {
    fun setProgressDialog(context: Context): Dialog {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.progress_indicator)
        return dialog
    }
}