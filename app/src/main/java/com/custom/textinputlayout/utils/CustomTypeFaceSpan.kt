package com.custom.textinputlayout.utils

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.TypefaceSpan

/**
 * Created by merttutsak on 26.04.2020.
 */

class CustomTypefaceSpan(family: String?, type: Typeface) :
    TypefaceSpan(family) {
    private val newType: Typeface = type
    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeFace(
            ds,
            newType
        )
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(
            paint,
            newType
        )
    }

    companion object {
        private fun applyCustomTypeFace(paint: Paint, tf: Typeface) {
            val oldStyle: Int
            val old = paint.typeface
            oldStyle = old?.style ?: 0
            val fake = oldStyle and tf.style.inv()
            if (fake and Typeface.BOLD != 0) {
                paint.isFakeBoldText = true
            }
            if (fake and Typeface.ITALIC != 0) {
                paint.textSkewX = -0.25f
            }
            paint.typeface = tf
        }
    }

}
