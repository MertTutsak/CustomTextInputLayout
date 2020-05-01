package com.custom.textinputlayout.custom.component

import android.content.res.TypedArray
import com.custom.textinputlayout.R

/**
 *   Created by mertttutsak on 26.04.2020.
 */

data class AttributeData(
    var startIcon: Int = -1,
    var startIconSize: Int = -1,
    var endIcon: Int = -1,
    var endIconSize: Int = -1,
    var hasHintAnimation: Boolean = true,
    var hasBottomLine: Boolean = true,
    var hint: String? = null,
    var hintSize: Int = -1,
    var hintColor: Int = -1,
    var hintFontFamily: Int = -1,
    var text: String? = null,
    var textSize: Int = -1,
    var textColor: Int = -1,
    var textFontFamily: Int = -1,
    var textMaxLength: Int = -1,
    var hasCounter: Boolean = false
) {
    fun initAttr(attributes: TypedArray): AttributeData {
        try {
            startIcon =
                attributes.getResourceId(
                    R.styleable.CustomTextInputlayout_cti_startIcon,
                    -1
                )

            startIconSize =
                attributes.getInteger(
                    R.styleable.CustomTextInputlayout_cti_startIconSize,
                    -1
                )

            endIcon =
                attributes.getResourceId(
                    R.styleable.CustomTextInputlayout_cti_endIcon,
                    -1
                )

            endIconSize =
                attributes.getInteger(
                    R.styleable.CustomTextInputlayout_cti_endIconSize,
                    -1
                )

            hasBottomLine =
                attributes.getBoolean(
                    R.styleable.CustomTextInputlayout_cti_bottomLine,
                    true
                )

            hasHintAnimation =
                attributes.getBoolean(
                    R.styleable.CustomTextInputlayout_cti_hintAnimation,
                    true
                )

            hint = attributes.getString(R.styleable.CustomTextInputlayout_cti_hint)
            hintSize =
                attributes.getInteger(R.styleable.CustomTextInputlayout_cti_hint_size, -1)
            hintColor =
                attributes.getInteger(R.styleable.CustomTextInputlayout_cti_hint_color, -1)
            hintFontFamily =
                attributes.getInteger(
                    R.styleable.CustomTextInputlayout_cti_hint_font_family,
                    -1
                )

            text = attributes.getString(R.styleable.CustomTextInputlayout_cti_text)
            textSize =
                attributes.getInteger(R.styleable.CustomTextInputlayout_cti_text_size, -1)
            textColor =
                attributes.getInteger(R.styleable.CustomTextInputlayout_cti_text_color, -1)
            textFontFamily =
                attributes.getInteger(
                    R.styleable.CustomTextInputlayout_cti_text_font_family,
                    -1
                )

            textMaxLength =
                attributes.getInteger(
                    R.styleable.CustomTextInputlayout_cti_text_max_length,
                    -1
                )

            hasCounter =
                attributes.getBoolean(
                    R.styleable.CustomTextInputlayout_cti_counter,
                    false
                )
        } catch (e: RuntimeException) {
            e.printStackTrace()
        } finally {
            attributes.recycle()
            return this
        }
    }
}