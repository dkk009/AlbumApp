package com.example.deepak.albumlist.utils

import android.app.Activity
import android.widget.Toast

class Utils {
    companion object {

        fun showError(activity: Activity, errorMessage: String) {
                Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}