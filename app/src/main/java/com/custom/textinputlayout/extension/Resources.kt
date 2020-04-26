package com.custom.textinputlayout.extension

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.view.View
import androidx.annotation.*
import androidx.fragment.app.Fragment

fun Context.resColor(@ColorRes colorRes: Int) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.resources.getColor(colorRes, null)
        } else {
            this.resources.getColor(colorRes)
        }

    } else {
        this.resources.getColor(colorRes)
    }

fun Context.resString(@StringRes stringRes: Int) = this.resources.getString(stringRes)

fun Context.resString(@StringRes stringRes: Int, vararg formatArgs: Any?) =
    this.resources.getString(stringRes, formatArgs)

fun Context.resDrawable(@DrawableRes drawableRes: Int, theme: Resources.Theme? = null) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.resources.getDrawable(drawableRes, theme)!!
        } else {
            this.resources.getDrawable(drawableRes)!!
        }
    } else {
        this.resources.getDrawable(drawableRes)!!
    }

fun Context.resDimenPx(@DimenRes dimenRes: Int) = this.resources.getDimensionPixelSize(dimenRes)

fun Context.resInt(@IntegerRes intRes: Int) = this.resources.getInteger(intRes)

fun Context.resBoolean(@BoolRes boolRes: Int) = this.resources.getBoolean(boolRes)

fun Context.resIntArray(@ArrayRes intArrRes: Int) = this.resources.getIntArray(intArrRes)

fun Context.resStrArray(@ArrayRes strArrRes: Int) = this.resources.getStringArray(strArrRes)


//fragment
fun Fragment.resColor(@ColorRes colorRes: Int, theme: Resources.Theme?) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.resources.getColor(colorRes, null)
        } else {
            this.resources.getColor(colorRes)
        }

    } else {
        this.resources.getColor(colorRes)
    }

fun Fragment.resString(@StringRes stringRes: Int) = this.resources.getString(stringRes)

fun Fragment.resString(@StringRes stringRes: Int, vararg formatArgs: Any?) =
    this.resources.getString(stringRes, formatArgs)

fun Fragment.resDrawable(@DrawableRes drawableRes: Int) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.resources.getDrawable(drawableRes, null)!!
        } else {
            this.resources.getDrawable(drawableRes)!!
        }
    } else {
        this.resources.getDrawable(drawableRes)!!
    }

fun Fragment.resDimenPx(@DimenRes dimenRes: Int) =
    this.resources.getDimensionPixelSize(dimenRes)

fun Fragment.resInt(@IntegerRes intRes: Int) = this.resources.getInteger(intRes)

fun Fragment.resBoolean(@BoolRes boolRes: Int) = this.resources.getBoolean(boolRes)

fun Fragment.resIntArray(@ArrayRes intArrRes: Int) = this.resources.getIntArray(intArrRes)

fun Fragment.resStrArray(@ArrayRes strArrRes: Int) = this.resources.getStringArray(strArrRes)

//view
fun View.resColor(@ColorRes colorRes: Int, theme: Resources.Theme?) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.resources.getColor(colorRes, null)
        } else {
            this.resources.getColor(colorRes)
        }
    } else {
        this.resources.getColor(colorRes)
    }

fun View.resString(@StringRes stringRes: Int) = this.resources.getString(stringRes)

fun View.resString(@StringRes stringRes: Int, vararg formatArgs: Any?) =
    this.resources.getString(stringRes, formatArgs)

fun View.resDrawable(@DrawableRes drawableRes: Int) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.resources.getDrawable(drawableRes, null)!!
        } else {
            this.resources.getDrawable(drawableRes)!!
        }
    } else {
        this.resources.getDrawable(drawableRes)!!
    }

fun View.resDimenPx(@DimenRes dimenRes: Int) = this.resources.getDimensionPixelSize(dimenRes)

fun View.resInt(@IntegerRes intRes: Int) = this.resources.getInteger(intRes)

fun View.resBoolean(@BoolRes boolRes: Int) = this.resources.getBoolean(boolRes)

fun View.resIntArray(@ArrayRes intArrRes: Int) = this.resources.getIntArray(intArrRes)

fun View.resStrArray(@ArrayRes strArrRes: Int) = this.resources.getStringArray(strArrRes)

fun Int.dpToPx() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.pxToDp() = (this / Resources.getSystem().displayMetrics.density).toInt()