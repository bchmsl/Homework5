package com.bchmsl.homework5.tools

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isInvisible

fun AppCompatTextView.makeError(error: String = "", enabled: Boolean){
    this.isInvisible = !enabled
    this.text = error
}