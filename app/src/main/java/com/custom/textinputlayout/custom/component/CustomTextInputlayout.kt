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
        initCounter()
    }

    private fun initTextInputLayout() {
        hintAnimator = HintAnimator(this, this.txtTitle, this.txtTitleAnimate, this.edtInput)
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
            this.edtInput.setHintTextColor(this.context.resColor(attr.hintColor))
            this.txtTitle.setTextColor(this.context.resColor(attr.hintColor))
            this.txtTitleAnimate.setTextColor(this.context.resColor(attr.hintColor))
        }
        if (attr.hintFontFamily != -1) {
            this.txtTitle.typeface = ResourcesCompat.getFont(context, attr.hintFontFamily)
            this.txtTitleAnimate.typeface = ResourcesCompat.getFont(context, attr.hintFontFamily)
        }
        (attr.hint ?: "").let {
            this.txtTitle.text = it
            this.txtTitleAnimate.text = it
        }
    }

    private fun initEdittext() {
        if (attr.textSize != -1) {
            this.edtInput.textSize = this.context.sp(attr.textSize).toFloat()
        }

        if (attr.textColor != -1) {
            this.edtInput.setTextColor(this.context.resColor(attr.textColor))
        }

        if (attr.textFontFamily != -1) {
            this.edtInput.typeface = ResourcesCompat.getFont(context, attr.textFontFamily)
        }

        if (attr.textMaxLength != -1) {
            this.edtInput.setMaxLength(attr.textMaxLength)
        }

        this.edtInput.onTextChanged { charSequence, i, i2, i3 ->
            hintAnimator.showIf(!charSequence.isNullOrEmpty())
            setCounter((charSequence ?: "").length)
        }

        attr.text.notNull { this.edtInput.setText(it) }
    }

    private fun initLine() {
        this.bottomLine.visibleIf(attr.hasBottomLine, true)
    }

    private fun initCounter() {
        if (attr.textMaxLength == -1 && attr.hasCounter) {
            throw Exception("max length of edittext must not be null")
        } else {
            txtCounter.visibleIf(attr.hasCounter)
            setCounter(this.edtInput.text.length)
        }
    }

    fun setStartIcon(icon:Int){
        this.startIcon.setImageResource(icon)
        this.startIcon.visible()
    }

    fun setStartIconVisible(isVisible:Boolean,saveSize:Boolean = false){
        this.startIcon.drawable.isNull{
            throw Exception("startIcon must not be empty")
        }
        this.startIcon.visibleIf(isVisible,saveSize)
    }

    fun setStartIconClickListener(onClick:(view:View)->Unit){
        this.startIcon.setOnDebouncedClickListener {
            onClick(it)
        }
    }

    fun setEndIcon(icon:Int){
        this.endIcon.setImageResource(icon)
        this.endIcon.visible()
    }

    fun setEndIconVisible(isVisible:Boolean,saveSize:Boolean = false){
        this.endIcon.drawable.isNull{
            throw Exception("endIcon must not be empty")
        }

        this.endIcon.visibleIf(isVisible,saveSize)
    }

    fun setEndIconClickListener(onClick:(view:View)->Unit){
        this.endIcon.setOnDebouncedClickListener {
            onClick(it)
        }
    }

    fun onTextChanged(onChanged:(text:String)->Unit){
        this.edtInput.onTextChanged{ charSequence: CharSequence?, i: Int, i1: Int, i2: Int ->
            onChanged(charSequence.toString())
        }
    }

    fun setCounter(length: Int) {
        txtCounter.text = "$length/${attr.textMaxLength}"
    }

    fun clear(){
        this.edtInput.setText("")
    }

}