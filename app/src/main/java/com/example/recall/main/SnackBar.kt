package com.example.recall.main

import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.example.recall.R

/**
 * Class that creates a custom Snackbar.
 */
@Suppress("DEPRECATION")
class Snackbar {
    companion object {
        fun showSnackbar(view: View, message: String) {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(view.context.resources.getColor(android.R.color.white))

            val textView = snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setTextColor(view.context.resources.getColor(R.color.primary))
            textView.textSize = 22f

            snackbar.show()
        }
    }
}
