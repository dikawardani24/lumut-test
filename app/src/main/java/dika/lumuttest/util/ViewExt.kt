package dika.lumuttest.util

import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import dika.lumuttest.R

fun TextView.setAsStatus(isCompleted: Boolean, isChangeTextColor: Boolean = true) {
    val textResId = if (isCompleted) R.string.completed else R.string.not_completed

    setText(textResId)

    if (isChangeTextColor) {
        val colorResId = if (isCompleted) R.color.green else R.color.red
        setTextColor(ContextCompat.getColor(context, colorResId))
    }
}

fun CardView.setAsStatus(isCompleted: Boolean) {
    val colorResId = if (isCompleted) R.color.green else R.color.red
    setCardBackgroundColor(ContextCompat.getColor(context, colorResId))
}