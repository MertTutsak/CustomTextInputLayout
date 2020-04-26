package com.custom.textinputlayout.extension

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.text.InputFilter
import android.widget.Filter


/**
 * this method is for text of edittext
 *
 * editTextEmail.isEmpty() -> true/false
 */
fun EditText.isEmpty(): Boolean {
    if (this.isNull()) return true
    if (this.text.trim().isEmpty()) return true

    return false
}

/**
 * this method is for text of edittext
 *
 * editTextEmail.isEmpty() -> true/false
 */
fun EditText.isNotEmpty(): Boolean {
    if (this.isNull()) return false
    if (this.text.isNullOrEmpty()) return false

    return true
}

/**
 * this method is for text of edittext
 *
 * this method is triggered after text is changed
 */
inline fun EditText?.afterTextChanged(crossinline afterTextChanged: (Editable?) -> Unit) {
    this?.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable)
        }
    })
}

/**
 * this method is for text of edittext
 *
 * this method is triggered when text is changed
 */
inline fun EditText?.onTextChanged(crossinline onTextChanged: (CharSequence?, Int, Int, Int) -> Unit) {
    this?.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onTextChanged.invoke(p0, p1, p2, p3)
        }

        override fun afterTextChanged(editable: Editable?) {
        }
    })
}

/**
 * this method is for text of edittext
 *
 * this method is triggered before text is changed
 */
inline fun EditText?.beforeTextChanged(crossinline beforeTextChanged: (CharSequence?, Int, Int, Int) -> Unit) {
    this?.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            beforeTextChanged.invoke(p0, p1, p2, p3)
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
        }
    })
}

fun EditText?.setMaxLength(length: Int) {
    val inputFilters = arrayOf<InputFilter>(InputFilter.LengthFilter(length))
    this?.filters?.forEach {
        inputFilters.plus(it)
    }

    this?.filters = inputFilters
}

fun View.focus() {
    if (this is EditText && !this.hasFocus()) {
        this.setSelection(this.text.length)
    } else {
        if (!this.hasFocus()) {
            this.requestFocus()
        }
    }
}