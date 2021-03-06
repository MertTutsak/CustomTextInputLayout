package com.custom.textinputlayout.custom.component

import android.content.res.TypedArray
import android.widget.TextView
import com.custom.textinputlayout.R

/**
 *   Created by mertttutsak on 26.04.2020.
 */

data class AttributeData(
    var startIcon: Int = -1,
    var startIconSize: Int = -1,

    var endIcon: Int = -1,
    var endIconSize: Int = -1,

    var hasBottomLine: Boolean = true,
    var bottomLineColor: Int = -1,

    var isAlignHintWithEdt: Boolean = false,
    var hasHintAnimation: Boolean = true,
    var hint: String? = null,
    var isHintEnable:Boolean = true,
    var hintSize: Int = -1,
    var hintColor: Int = -1,
    var hintFontFamily: Int = -1,

    var text: String? = null,
    var textSize: Int = -1,
    var textColor: Int = -1,
    var textFontFamily: Int = -1,
    var textMaxLength: Int = -1,

    var infoFontFamily: Int = -1,
    var hasInfo: Boolean = true,
    var isLineColorWithInfo: Boolean = false,

    var defaultText: String = "",
    var defaultIcon: Int = -1,
    var defaultColor: Int = -1,

    var errorText: String = "",
    var errorIcon: Int = -1,
    var errorColor: Int = -1,

    var warningText: String = "",
    var warningIcon: Int = -1,
    var warningColor: Int = -1,

    var successText: String = "",
    var successIcon: Int = -1,
    var successColor: Int = -1,

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

            bottomLineColor =
                attributes.getInteger(R.styleable.CustomTextInputlayout_cti_bottomLine_color, -1)

            hasHintAnimation =
                attributes.getBoolean(
                    R.styleable.CustomTextInputlayout_cti_hintAnimation,
                    true
                )

            isAlignHintWithEdt = attributes.getBoolean(
                R.styleable.CustomTextInputlayout_cti_is_align_hint_with_edt,
                false
            )
            isHintEnable= attributes.getBoolean(
                R.styleable.CustomTextInputlayout_cti_hint_enable,
                true
            )
            hint = attributes.getString(R.styleable.CustomTextInputlayout_cti_hint)
            hintSize =
                attributes.getInteger(R.styleable.CustomTextInputlayout_cti_hint_size, -1)
            hintColor =
                attributes.getInteger(R.styleable.CustomTextInputlayout_cti_hint_color, -1)
            /*hintFontFamily =
                attributes.getInteger(
                    R.styleable.CustomTextInputlayout_cti_hint_font_family,
                    -1
                )*/

            text = attributes.getString(R.styleable.CustomTextInputlayout_cti_text)
            textSize =
                attributes.getInteger(R.styleable.CustomTextInputlayout_cti_text_size, -1)
            textColor =
                attributes.getInteger(R.styleable.CustomTextInputlayout_cti_text_color, -1)
            /*textFontFamily =
                attributes.getInteger(
                    R.styleable.CustomTextInputlayout_cti_text_font_family,
                    -1
                )*/

            textMaxLength =
                attributes.getInteger(
                    R.styleable.CustomTextInputlayout_cti_text_max_length,
                    -1
                )

            /*infoFontFamily =
                attributes.getInteger(
                    R.styleable.CustomTextInputlayout_cti_info_font_family,
                    -1
                )*/

            hasInfo = attributes.getBoolean(
                R.styleable.CustomTextInputlayout_cti_hasInfo,
                true
            )

            isLineColorWithInfo =
                attributes.getBoolean(
                    R.styleable.CustomTextInputlayout_cti_line_color_with_info,
                    false
                )

            defaultText =
                attributes.getString(R.styleable.CustomTextInputlayout_cti_default_text) ?: ""
            defaultIcon = attributes.getResourceId(
                R.styleable.CustomTextInputlayout_cti_default_icon,
                -1
            )
            defaultColor =
                attributes.getInteger(R.styleable.CustomTextInputlayout_cti_default_color, -1)

            errorText = attributes.getString(R.styleable.CustomTextInputlayout_cti_error_text) ?: ""
            errorIcon = attributes.getResourceId(
                R.styleable.CustomTextInputlayout_cti_error_icon,
                -1
            )
            errorColor =
                attributes.getInteger(R.styleable.CustomTextInputlayout_cti_error_color, -1)

            warningText =
                attributes.getString(R.styleable.CustomTextInputlayout_cti_warning_text) ?: ""
            warningIcon = attributes.getResourceId(
                R.styleable.CustomTextInputlayout_cti_warning_icon,
                -1
            )
            warningColor =
                attributes.getInteger(R.styleable.CustomTextInputlayout_cti_warning_color, -1)

            successText =
                attributes.getString(R.styleable.CustomTextInputlayout_cti_success_text) ?: ""
            successIcon = attributes.getResourceId(
                R.styleable.CustomTextInputlayout_cti_success_icon,
                -1
            )
            successColor =
                attributes.getInteger(R.styleable.CustomTextInputlayout_cti_success_color, -1)

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