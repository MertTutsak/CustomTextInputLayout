package com.custom.textinputlayout.extension

import java.lang.Exception

/**Example**/
/**
 * var any:Any? = null
 * if(any.isNull()) -> true
 * any = Any()
 *
 * if(any.isNull()) -> false
 * */
inline fun Any?.isNull(): Boolean {
    return (this == null)
}

/**Example**/
/**
 * var any:Any? = null
 * any.isNull(object :ReturnListener{
 *
 *  override fun onTrue() {
 *      this line runs
 *  }
 *
 *  override fun onFalse() {
 *      this line doesn't run
 *  }
 *
 * }
 * )
 * */
fun Any?.isNull(listener: ReturnListener): Boolean {
    if (this.isNull()) {
        listener.onTrue()
    } else {
        listener.onFalse()
    }

    return (this == null)
}

/**Example**/
/**
 * var any:Any? = null
 * if(any.isNull()) -> false
 * any = Any()
 *
 * if(any.isNull()) -> true
 * */
inline fun Any?.isNotNull(): Boolean {
    return (this != null)
}

/**Example**/
/**
 * var any:Any? = null
 * any.isNull(object :ReturnListener{
 *
 *  override fun onTrue() {
 *      this line doesn't run
 *  }
 *
 *  override fun onFalse() {
 *      this line runs
 *  }
 *
 * }
 * )
 * */
inline fun Any?.isNotNull(listener: ReturnListener): Boolean {
    if (this.isNotNull()) {
        listener.onTrue()
    } else {
        listener.onFalse()
    }
    return (this != null)
}

/**
 * if the variable isn't null, the function runs
 */
inline fun <R> R?.notNull(f: (it: R) -> Unit) {
    if (this != null) {
        f(this)
    }
}

inline fun <R> R?.isNull(f: (it: R?) -> Unit) {
    if (this == null) {
        f(this)
    }
}

interface ReturnListener {
    fun onTrue()
    fun onFalse()
}

/**
 * If toString convert from a null integer variable to string variable in the kotlin default, result is seen "null".
 * The extension is seen empty ("").
 *
 * /** Example **/
 *
 * kotlin default extension ->
 * var i:Int? = null
 * i.toString() -> null
 * custom extension  ->
 * var i:Int? = null
 * i.toString() ->
 */
inline fun Any?.toString(): String {
    return if (this.isNull()) {
        ""
    } else {
        try {
            this!!.toString()
        } catch (e: Exception) {
            ""
        }
    }
}

fun <T> Any?.castOrNull(): T? {
    return try {
        this as T
    }catch (e:Exception){
        null
    }
}
