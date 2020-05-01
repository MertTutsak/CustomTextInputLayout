package com.custom.textinputlayout.custom.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.custom.textinputlayout.R
import com.custom.textinputlayout.extension.*
import kotlinx.android.synthetic.main.component_textinputlayout.view.*
import java.lang.Exception

/**
 *   Created by mertttutsak on 25.04.2020.
 */

class CustomTextInputlayout : LinearLayout {

    var attr = AttributeData()
    lateinit var hintAnimator: HintAnimator
    var infoState = INFO_STATE.DEFAULT
        set(value) {
            if (field != value && onInfoStateChanged.isNotNull()) {
                onInfoStateChanged!!(value)
            }
            field = value
        }
    private var onInfoStateChanged: ((state: INFO_STATE) -> Unit)? = null

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

        attr.initAttr(
            context.obtainStyledAttributes(
                attrs,
                R.styleable.CustomTextInputlayout
            )
        )

        initTextInputLayout()
        initStartIcon()
        initEndIcon()
        initHint()
        initEdittext()
        initLine()
        initInfo()
        initCounter()
    }

    private fun initTextInputLayout() {
        hintAnimator = HintAnimator(
            this,
            this.txtTitle,
            this.txtTitleAnimate,
            this.edtInput,
            this.attr.hasHintAnimation,
            this.attr.isAlignHintWithEdt
        )
        this.onTouch {
            if (!it) {
                this.edtInput.focus()
            }
        }
    }

    private fun initStartIcon() {
        if (attr.startIcon != -1) {
            if (attr.startIconSize != -1) {
                this.startIcon.layoutParams.width = attr.startIconSize.dp
                this.startIcon.layoutParams.height = attr.startIconSize.dp
            }
            this.setStartIcon(attr.startIcon)
        }
    }

    private fun initEndIcon() {
        if (attr.endIcon != -1) {
            if (attr.endIconSize != -1) {
                this.endIcon.layoutParams.width = attr.endIconSize.dp
                this.endIcon.layoutParams.height = attr.endIconSize.dp
            }
            this.setEndIcon(attr.endIcon)
        }
    }

    private fun initHint() {
        if (attr.hintSize != -1) {
            this.txtTitle.textSize = this.context.sp(attr.hintSize).toFloat()
            this.txtTitleAnimate.textSize = this.context.sp(attr.hintSize).toFloat()
        }
        if (attr.hintColor != -1) {
            this.edtInput.setHintTextColor(attr.hintColor)
            this.txtTitle.setTextColor(attr.hintColor)
            this.txtTitleAnimate.setTextColor(attr.hintColor)
        }
        if (attr.hintFontFamily != -1) {
            this.txtTitle.typeface = ResourcesCompat.getFont(context, attr.hintFontFamily)
            this.txtTitleAnimate.typeface = ResourcesCompat.getFont(context, attr.hintFontFamily)
        }
        (attr.hint ?: "").let {
            this.txtTitle.text = it
            this.txtTitleAnimate.text = it
        }
        if (!attr.isHintEnable) {
            this.txtTitle.gone()
            this.txtTitleAnimate.gone()
        }
    }

    private fun initEdittext() {
        if (attr.textSize != -1) {
            this.edtInput.textSize = this.context.sp(attr.textSize).toFloat()
        }

        if (attr.textColor != -1) {
            this.edtInput.setTextColor(attr.textColor)
        }

        if (attr.textFontFamily != -1) {
            this.edtInput.typeface = ResourcesCompat.getFont(context, attr.textFontFamily)
        }

        if (attr.textMaxLength != -1) {
            this.edtInput.setMaxLength(attr.textMaxLength)
        }

        this.edtInput.onTextChanged { charSequence, i, i2, i3 ->
            if (attr.isHintEnable) {
                hintAnimator.showIf(!charSequence.isNullOrEmpty())
            }
            setCounter((charSequence ?: "").length)
        }

        attr.text.notNull { this.edtInput.setText(it) }
        if (attr.isHintEnable) {
            waitForLayout {
                hintAnimator.showIf(!getText().isNullOrEmpty())
            }
        } else {
            (attr.hint ?: "").let {
                this.edtInput.setHint(it)
            }
        }
    }

    private fun initLine() {
        if (attr.hasBottomLine) {
            if (attr.isLineColorWithInfo && infoState == INFO_STATE.DEFAULT && attr.defaultColor != -1) {
                this.bottomLine.setBackgroundColor(attr.defaultColor)
            } else if (attr.isLineColorWithInfo && infoState == INFO_STATE.ERROR && attr.errorColor != -1) {
                this.bottomLine.setBackgroundColor(attr.errorColor)
            } else if (attr.isLineColorWithInfo && infoState == INFO_STATE.WARNING && attr.warningColor != -1) {
                this.bottomLine.setBackgroundColor(attr.warningColor)
            } else if (attr.isLineColorWithInfo && infoState == INFO_STATE.SUCCESS && attr.successColor != -1) {
                this.bottomLine.setBackgroundColor(attr.successColor)
            } else if (attr.bottomLineColor != -1) {
                this.bottomLine.setBackgroundColor(attr.bottomLineColor)
            }
        }

        this.bottomLine.visibleIf(attr.hasBottomLine, true)
    }

    private fun setInfoFontFamily() {
        if (attr.infoFontFamily != -1) {
            this.txtInfo.typeface = ResourcesCompat.getFont(context, attr.infoFontFamily)
        }
    }

    private fun initInfo() {
        if (!attr.hasInfo) {
            infoVisible(attr.hasInfo)
        } else {
            if (attr.defaultIcon == -1) {
                attr.defaultIcon = android.R.color.transparent
            }

            if (attr.defaultColor == -1) {
                attr.defaultColor = this.txtInfo.currentTextColor
            }

            initDefault()
        }
    }

    private fun initDefault(text: String = "") {
        this.infoIcon.setImageResource(attr.defaultIcon)
        if (!text.isNullOrEmpty()) {
            this.txtInfo.setText(text)
        } else {
            this.txtInfo.setText(attr.defaultText)
        }
        this.txtInfo.setTextColor(attr.defaultColor)
        this.setInfoFontFamily()
    }

    private fun initError(text: String = "") {
        if (attr.errorIcon != -1) {
            this.infoIcon.setImageResource(attr.errorIcon)
        } else {
            this.infoIcon.setImageResource(attr.defaultIcon)
        }

        if (!text.isNullOrEmpty()) {
            this.txtInfo.setText(text)
        } else {
            this.txtInfo.setText(attr.errorText)
        }
        if (attr.errorColor != -1) {
            this.txtInfo.setTextColor(attr.errorColor)
        } else {
            this.txtInfo.setTextColor(attr.defaultColor)
        }

        this.setInfoFontFamily()
    }

    private fun initWarning(text: String = "") {
        if (attr.warningIcon != -1) {
            this.infoIcon.setImageResource(attr.warningIcon)
        } else {
            this.infoIcon.setImageResource(attr.defaultIcon)
        }

        if (!text.isNullOrEmpty()) {
            this.txtInfo.setText(text)
        } else {
            this.txtInfo.setText(attr.warningText)
        }
        if (attr.warningColor != -1) {
            this.txtInfo.setTextColor(attr.warningColor)
        } else {
            this.txtInfo.setTextColor(attr.defaultColor)
        }

        this.setInfoFontFamily()
    }

    private fun initSuccess(text: String = "") {
        if (attr.successIcon != -1) {
            this.infoIcon.setImageResource(attr.successIcon)
        } else {
            this.infoIcon.setImageResource(attr.defaultIcon)
        }

        if (!text.isNullOrEmpty()) {
            this.txtInfo.setText(text)
        } else {
            this.txtInfo.setText(attr.successText)
        }
        if (attr.successColor != -1) {
            this.txtInfo.setTextColor(attr.successColor)
        } else {
            this.txtInfo.setTextColor(attr.defaultColor)
        }

        this.setInfoFontFamily()
    }

    private fun initCounter() {
        if (attr.textMaxLength == -1 && attr.hasCounter) {
            throw Exception("max length of edittext must not be null")
        } else {
            counterVisible(attr.hasCounter)
            setCounter(this.edtInput.text.length)
        }
    }

    fun setStartIcon(icon: Int) {
        this.startIcon.setImageResource(icon)
        setStartIconVisible(true)
    }

    fun setStartIconVisible(isVisible: Boolean, saveSize: Boolean = false) {
        this.startIcon.drawable.isNull {
            throw Exception("startIcon must not be empty")
        }
        this.startIcon.visibleIf(isVisible, saveSize)
        hintAnimator.hintAlign()
    }

    fun setStartIconClickListener(onClick: (view: View) -> Unit) {
        this.startIcon.setOnDebouncedClickListener {
            onClick(it)
        }
    }

    fun setEndIcon(icon: Int) {
        this.endIcon.setImageResource(icon)
        setEndIconVisible(true)
    }

    fun setEndIconVisible(isVisible: Boolean, saveSize: Boolean = false) {
        this.endIcon.drawable.isNull {
            throw Exception("endIcon must not be empty")
        }

        this.endIcon.visibleIf(isVisible, saveSize)
    }

    fun setEndIconClickListener(onClick: (view: View) -> Unit) {
        this.endIcon.setOnDebouncedClickListener {
            onClick(it)
        }
    }

    fun setInfoState(infoState: INFO_STATE, text: String = "") {
        this.infoState = infoState

        when (this.infoState) {
            INFO_STATE.DEFAULT -> {
                initDefault(text)
            }
            INFO_STATE.ERROR -> {
                initError(text)
            }
            INFO_STATE.WARNING -> {
                initWarning(text)
            }
            INFO_STATE.SUCCESS -> {
                initSuccess(text)
            }
        }

        initLine()
    }

    fun infoVisible(hasInfo: Boolean, saveSize: Boolean = false) {
        this.infoIcon.visibleIf(attr.hasInfo, saveSize)
        this.txtInfo.visibleIf(attr.hasInfo, saveSize)
    }

    fun setCounter(length: Int) {
        txtCounter.text = "$length/${attr.textMaxLength}"
    }

    fun counterVisible(hasInfo: Boolean, saveSize: Boolean = false) {
        this.txtCounter.visibleIf(hasInfo, saveSize)
    }

    fun clear() {
        if (!getText().isNullOrEmpty()) {
            this.edtInput.setText("")
        }
    }

    fun onTextChanged(onChanged: (text: String) -> Unit) {
        this.edtInput.onTextChanged { charSequence: CharSequence?, i: Int, i1: Int, i2: Int ->
            onChanged(charSequence.toString())
        }
    }

    fun onInfoStateChanged(onChanged: (state: INFO_STATE) -> Unit) {
        onInfoStateChanged = onChanged
    }

    fun getInfoText(): String = this.txtInfo.text.toString()

    fun getText(): String = this.edtInput.text.toString()

    fun getHint(): String = this.txtTitle.text.toString()

    fun addImeOptions(imeOptions: Int) {
        edtInput.imeOptions = edtInput.imeOptions or imeOptions
    }

    fun setImeOptions(imeOptions: Int) {
        edtInput.imeOptions = imeOptions
    }

    fun setInputType(inputType: Int) {
        edtInput.inputType = inputType
    }

}