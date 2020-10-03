package com.android_dev_challenge.sliide.common.util

import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.android_dev_challenge.sliide.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object Utility {

    fun showAlertDialog(context: Context, view: Int, title: String, positiveButton: String) {

        val dialog: AlertDialog = MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setView(getDialogView(context, view))
            .setPositiveButton(positiveButton, null)
            .setNegativeButton(
                context.getString(
                    R.string.dialog_btn_cancel
                )
            ) { dialog: DialogInterface, _: Int -> dialog.dismiss() }
            .setCancelable(false)
            .create()

        dialog.show()

        setAlertDialogPositiveButtonClickListener(view, context, dialog)
    }

    private fun setAlertDialogPositiveButtonClickListener(
        view: Int,
        context: Context,
        dialog: AlertDialog
    ) {
        val dialogView = getDialogView(context, view)

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            when (view) {
                R.layout.dialog_add_new_user -> {
                    val userNameEditText = dialogView.findViewById<EditText>(R.id.userNameEditText)
                    val userEmailEditText =
                        dialogView.findViewById<EditText>(R.id.userEmailEditText)

                    val userNameEditTextValue = userNameEditText?.text.toString()
                    val userEmailEditTextValue = userEmailEditText?.text.toString()

                    if (TextUtils.isEmpty(userNameEditTextValue)) {
                        userNameEditText?.error =
                            context.getString(R.string.edit_text_error_user_name)
                    }

                    if (TextUtils.isEmpty(userEmailEditTextValue)) {
                        userEmailEditText?.error =
                            context.getString(R.string.edit_text_error_user_email)
                    }

                    if (!Patterns.EMAIL_ADDRESS.matcher(userEmailEditTextValue).matches()) {
                        userEmailEditText?.error =
                            context.getString(R.string.edit_text_error_user_email_validation)
                    }
                }
            }
        }
    }

    private fun getDialogView(context: Context, view: Int): View {
        return LayoutInflater.from(context).inflate(view, null)
    }
}