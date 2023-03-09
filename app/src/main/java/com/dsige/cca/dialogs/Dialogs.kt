package com.dsige.cca.dialogs

import android.content.Context
import androidx.appcompat.view.ContextThemeWrapper
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.dsige.cca.R

object Dialogs {

    fun Context.messagePermission(
        title: String,
        message: String,
        onDismiss: (isConfirm : Boolean) -> Unit
    ) {
        MaterialAlertDialogBuilder(
            ContextThemeWrapper(
                this,
                R.style.Theme_APPCCA
            )
        ).setTitle(title)
            .setMessage(message)
            .setPositiveButton("De acuerdo") { dialogInterface, _ ->
                onDismiss(true)
                dialogInterface.dismiss()
            }
            .setNegativeButton("No") { dialogInterface, _ ->
                onDismiss(false)
                dialogInterface.dismiss()
            }
            .setCancelable(false)
            .show()
    }
}