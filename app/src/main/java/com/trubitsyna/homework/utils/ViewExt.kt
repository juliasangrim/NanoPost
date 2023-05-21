package com.trubitsyna.homework.utils

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import com.trubitsyna.homework.R

fun View.replace(newView: View) {
    this.visibility = View.GONE
    newView.visibility = View.VISIBLE
}

fun View.showError(@StringRes resId: Int) {
    Snackbar.make(this, resId, Snackbar.LENGTH_SHORT)
        .show()
}

fun View.showErrorWithAction(@StringRes resId: Int, action: () -> Unit) {
    Snackbar.make(this, resId, Snackbar.LENGTH_LONG)
        .setAction(R.string.action_text) {
            action.invoke()
        }
        .show()
}

