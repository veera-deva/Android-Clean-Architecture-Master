package com.demo.common_ui.utils

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog

object AlertDialogUtils {

    fun showAlertDialog(
        context: Context,
        title: String = "Information",
        message: String = Constants.DEFAULT_ERROR_MESSAGE,
        positiveButton: String? = null,
        negativeButton: String? = null,
        positiveOnClickListener: View.OnClickListener? = null,
        negativeOnClickListener: View.OnClickListener? = null
    ) {

        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)

        builder.setMessage(message)

        builder.setCancelable(true)
        positiveButton?.run {
            builder.setPositiveButton(positiveButton) { dialog, _ ->
                dialog.dismiss()
                positiveOnClickListener?.onClick(null)

            }
        }
        negativeButton?.run {
            builder.setNegativeButton(negativeButton) { dialog, _ ->
                negativeOnClickListener?.onClick(null)
                dialog.dismiss()
            }
        }
        builder.show()
    }
}