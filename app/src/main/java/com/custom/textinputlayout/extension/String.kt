package com.custom.textinputlayout.extension

import android.graphics.Typeface
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.AbsoluteSizeSpan
import android.view.View
import android.widget.EditText
import com.loodos.frink.utility.utils.CustomTypefaceSpan
import java.util.*
import java.util.regex.Pattern

/**Examples**/
/**
 * var text:String? = "Lorem Ipsum is simply dummy text of the printing"
 * text?.isNullOrEmpty() -> false
 *
 * text = null
 * text?.isNullOrEmpty() -> true
 *
 * text = ""
 * text?.isNullOrEmpty() -> true
 */
fun String?.isNullOrEmpty(listener: ReturnListener): Boolean {
    if (!this.isNull() && this!!.isNotEmpty()) {
        listener.onFalse()
    } else {
        listener.onTrue()
    }

    return (this == null || this == "")
}

/**Examples**/
/**
 * var text:String = "Lorem Ipsum"
 * text.removeFirstAndLastChar() -> "orem Ipsu"
 */
fun String.removeFirstAndLastChar(): String = this.substring(1, this.length - 1)

/**Examples**/
/**
 * var text:String = "loRem ipSum"
 * text.toFirstUpperCase() -> "Lorem ipsum"
 */
fun String.toFirstUpperCase(): String {
    if (this.isEmpty()) return ""

    val firstChar: String = this[0].toString().toUpperCase(Locale.getDefault())
    return firstChar + this.substring(1, this.lastIndex).toLowerCase(Locale.getDefault())
}

/**Examples**/
/**
 * var text:String = "Lorem Ipsum"
 * var text2:String = "Lorem Ipsum is"
 * text.getMore(text2) -> "Lorem Ipsum is" (text2)
 * --
 * var text:String = "Lorem Ipsum is"
 * var text2:String = "Lorem Ipsum"
 * text.getMore(text2) -> "Lorem Ipsum is" (text)
 * --
 * var text:String = "Lorem Ipsum"
 * var text2:String = "Lorem Ipsum"
 * text.getMore(text2) -> "Lorem Ipsum is" (text)
 */
fun String.getMore(text: String): String {
    return if (this.isEmpty() && text.isEmpty()) ""
    else if (this.isEmpty() && text.isNotEmpty()) text
    else if (this.isNotEmpty() && text.isEmpty()) this
    else {
        if (this.length >= text.length) {
            this
        } else {
            text
        }
    }
}

/**Examples**/
/**
 * var text:String = "Lorem Ipsum"
 * var text2:String = "Lorem Ipsum is"
 * text.isMoreThanText(text2) -> false
 * --
 * var text:String = "Lorem Ipsum is"
 * var text2:String = "Lorem Ipsum"
 * text.isMoreThanText(text2) -> false
 * --
 * var text:String = "Lorem Ipsum"
 * var text2:String = "Lorem Ipsum"
 * text.isMoreThanText(text2) -> true
 */
fun String.isMoreThanText(text: String): Boolean {
    if (this.isEmpty() && text.isEmpty()) return false
    else if (this.isEmpty() && text.isNotEmpty()) return false
    else if (this.isNotEmpty() && text.isEmpty()) return true
    else {
        return this.length >= text.length
    }
}

/**Examples**/
/**
 * var text:String = "Lorem Ipsum"
 * var count:Int = 5
 * text.isMoreThanCount(count) -> true
 * --
 * var text:String = "Lorem"
 * var count:Int = 5
 * text.isMoreThanCount(count) -> false
 * --
 * * var text:String = "Lorem"
 * var count:Int = 4
 * text.isMoreThanCount(count) -> true
 */
fun String.isMoreThanCount(count: Int): Boolean {
    return if (this.isNull()) false
    else (this.isEmpty() && count <= 0) || this.length > count
}

/**Examples**/
/**
 * var text:String = "Lorem Ipsum"
 * var count:Int = 5
 * text.isEqualCount(count) -> false
 * --
 * var text:String = "Lorem"
 * var count:Int = 5
 * text.isEqualCount(count) -> true
 * --
 * * var text:String = "Lorem"
 * var count:Int = 4
 * text.isEqualCount(count) -> false
 */
fun String.isEqualCount(count: Int): Boolean {
    return if (this.isNull()) false
    else (this.length == count)
}

/**Examples**/
/**
 * var text:String = "Lorem Ipsum is simply dummy text of the printing"
 *
 * text.countSpaces() -> 8
 */
fun String.countSpaces(): Int {
    return this.count { c -> c == ' ' }
}

/**Examples**/
/**
 * var text:String = "Lorem Ipsum is simply dummy text of the printing"
 *
 * text.replaceAtIndex('a', 4) -> "Lorea Ipsum is simply dummy text of the printing"
 */
fun String.replaceAtIndex(character: Char, index: Int): String {
    if (this.isEmpty()) return ""
    if (this.isMoreThanCount(index) || this.isEqualCount(index)) {
        var replaceText = ""
        for (i in this.indices) {
            replaceText += if (i == index) {
                character
            } else {
                this[i]
            }
        }

        return replaceText
    }

    return this
}

/**Examples**/
/**
 * var text:String = "Lörem ıpsum"
 *
 * text.convertToInsiderFormat() -> "Lorem ipsum"
 */
fun String.convertToInsiderFormat(): String {
    var itemId = this
    itemId = itemId.toLowerCase(Locale.getDefault())
    itemId = itemId.replace("ı", "i")
    itemId = itemId.replace("ö", "o")
    itemId = itemId.replace("ü", "u")
    itemId = itemId.replace("ş", "s")
    itemId = itemId.replace("ğ", "g")
    itemId = itemId.replace("ç", "c")
    itemId = itemId.replace("Ü", "U")
    itemId = itemId.replace("İ", "I")
    itemId = itemId.replace("Ö", "O")
    itemId = itemId.replace("Ü", "U")
    itemId = itemId.replace("Ş", "S")
    itemId = itemId.replace("Ğ", "G")
    itemId = itemId.replace("Ç", "C")
    itemId = itemId.replace(" ", "_")
    itemId = itemId.replace("-", "_")
    return itemId
}


/**Examples**/
/**
 * var text:String = "287346"
 *
 * text.isValid("^[0-9]{6}$") -> true
 * text.isValid("^[A-Z]{6}$") -> false
 */
fun String.isValid(RegExp: String): Boolean {
    val pattern = Pattern.compile(RegExp, Pattern.CASE_INSENSITIVE)
    return isValid(pattern)
}

/**Examples**/
/**
 * var text:String = "loodos@loodos.com"
 *
 * text.isValid(Patterns.EMAIL_ADDRESS) -> true
 * text.isValid(Patterns.PHONE) -> false
 */
fun String.isValid(pattern: Pattern): Boolean {
    return (pattern.matcher(this).matches())
}