package com.custom.textinputlayout.custom.component

import android.content.res.TypedArray
import com.custom.textinputlayout.R
import com.custom.textinputlayout.extension.isNull
import java.util.jar.Attributes

/**
 *   Created by mertttutsak on 26.04.2020.
 */
data class AttributeData(
    var startIcon: Int = -1,
    var endIcon: Int = -1,
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
//
        fun initAttr(attributes: TypedArray): AttributeData {
            val instance = AttributeData()
            try {
                instance.startIcon =
                    attributes.getResourceId(
                        R.styleable.CustomTextInputlayout_cti_startIcon,
                        -1
                    )

                instance.endIcon =
                    attributes.getResourceId(
                        R.styleable.CustomTextInputlayout_cti_startIcon,
                        -1
                    )

                instance.hasBottomLine =
                    attributes.getBoolean(
                        R.styleable.CustomTextInputlayout_cti_bottomLine,
                        true
                    )

                instance.hasHintAnimation =
                    attributes.getBoolean(
                        R.styleable.CustomTextInputlayout_cti_hintAnimation,
                        true
                    )

                instance.hint = attributes.getString(R.styleable.CustomTextInputlayout_cti_hint)
                instance.hintSize =
                    attributes.getInteger(R.styleable.CustomTextInputlayout_cti_hint_size, -1)
                instance.hintColor =
                    attributes.getInteger(R.styleable.CustomTextInputlayout_cti_hint_color, -1)
                instance.hintFontFamily =
                    attributes.getInteger(
                        R.styleable.CustomTextInputlayout_cti_hint_font_family,
                        -1
                    )

                instance.text = attributes.getString(R.styleable.CustomTextInputlayout_cti_text)
                instance.textSize =
                    attributes.getInteger(R.styleable.CustomTextInputlayout_cti_text_size, -1)
                instance.textColor =
                    attributes.getInteger(R.styleable.CustomTextInputlayout_cti_text_color, -1)
                instance.textFontFamily =
                    attributes.getInteger(
                        R.styleable.CustomTextInputlayout_cti_text_font_family,
                        -1
                    )

                instance.textMaxLength =
                    attributes.getInteger(
                        R.styleable.CustomTextInputlayout_cti_text_max_length,
                        -1
                    )

                instance.hasCounter =
                    attributes.getBoolean(
                        R.styleable.CustomTextInputlayout_cti_counter,
                        false
                    )
            } catch (e: RuntimeException) {
                e.printStackTrace()
            } finally {
                attributes.recycle()
            }
            return instance
        }
}