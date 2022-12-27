package com.mohamedalaa4j.storeapp.utilities

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.mohamedalaa4j.storeapp.R

object Utilities {

    private lateinit var progressbarDialog: Dialog
    private var doubleBackToExitPressedOnce = false

    fun showProgressDialog(context: Context) {
        progressbarDialog = Dialog(context)
        progressbarDialog.setCancelable(false)
        progressbarDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressbarDialog.setContentView(R.layout.progress_dialog)
        progressbarDialog.show()
    }

    fun cancelProgressDialog() {
        if (progressbarDialog.isShowing) {
            progressbarDialog.dismiss()
        }
    }

    fun doubleTapToExit(activity: Activity) {

        if (doubleBackToExitPressedOnce) {
            activity.finish()
            return
        }

        doubleBackToExitPressedOnce = true
        Toast.makeText(activity, activity.getString(R.string.click_back_again_to_exit), Toast.LENGTH_SHORT)
            .show()

        Handler(Looper.getMainLooper()).postDelayed(
            { doubleBackToExitPressedOnce = false },
            2000
        )
    }

}