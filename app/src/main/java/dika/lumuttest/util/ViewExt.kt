package dika.lumuttest.util

import android.widget.TextView
import androidx.core.content.ContextCompat
import dika.lumuttest.R

fun TextView.setAsStatus(isCompleted: Boolean) {
    val textResId = if (isCompleted) R.string.completed else R.string.not_completed
    val colorResId = if (isCompleted) R.color.green else R.color.red

    setText(textResId)
    setTextColor(ContextCompat.getColor(context, colorResId))
}