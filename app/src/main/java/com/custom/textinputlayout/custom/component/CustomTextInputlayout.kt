package com.custom.textinputlayout.custom.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.custom.textinputlayout.R
import com.custom.textinputlayout.extension.*
import kotlinx.android.synthetic.main.component_textinputlayout.view.*

/**
 *   Created by mertttutsak on 25.04.2020.
 */
class CustomTextInputlayout : LinearLayout {

    var startIcon: Int = -1
    var endIcon: Int = -1
    var hasHintAnimation = true
    var hasBottomLine = true

    var text: String? = null
    var hint: String? = null

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        View.inflate(context, R.layout.component_textinputlayout, this)

        if (attrs != null) {
            val attributes =
                context.obtainStyledAttributes(attrs, R.styleable.CustomTextInputlayout)
            try {
                startIcon =
                    attributes.getResourceId(
                        R.styleable.CustomTextInputlayout_cti_startIcon,
                        -1
                    )

                endIcon =
                    attributes.getResourceId(
                        R.styleable.CustomTextInputlayout_cti_startIcon,
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
                text = attributes.getString(R.styleable.CustomTextInputlayout_cti_text)

                attributes.recycle()
            } catch (e: RuntimeException) {
                e.printStackTrace()
            }
        }

        initTextInputLayout()
        initEdittext()
        initLine()
    }

    private fun initTextInputLayout() {
        this.onTouch {
            if (!it) {
                this.edtInput.focus()
            }
        }
    }

    private fun initTitle() {
        this.edtInput.hint = text
        if (text.isNullOrEmpty()) {
            this.txtTitle.text = hint
        }
    }

    private fun initEdittext() {
        this.edtInput.onTextChanged { charSequence, i, i2, i3 ->
            text = charSequence.toString()
            initTitle()
        }
        text.notNull { this.edtInput.setText(it) }
    }

    private fun initLine() {
        this.bottomLine.visibleIf(hasBottomLine, true)
    }


}