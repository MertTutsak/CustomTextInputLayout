package com.custom.textinputlayout.custom.component

import android.animation.*
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

    val showAnimator = AnimatorSet()
    val hideAnimator = AnimatorSet()

    var startIcon: Int = -1
    var endIcon: Int = -1
    var hasHintAnimation = true
    var hasBottomLine = true

    var hint: String? = null
    var hintSize: Int = -1
    var hintColor: Int = -1
    var hintFontFamily: Int = -1
    var text: String? = null
    var textSize: Int = -1
    var textColor: Int = -1
    var textFontFamily: Int = -1
    var textMaxLength: Int = -1

    var hasCounter: Boolean = false

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
            }
        }

        initTextInputLayout()
        initHint()
        initEdittext()
        initLine()
        initCounter()
    }

    private fun initTextInputLayout() {
        this.onTouch {
            if (!it) {
                this.edtInput.focus()
            }
        }
    }

    private fun initHint() {
        if (hintSize != -1) {
            this.txtTitle.textSize = this.context.sp(hintSize).toFloat()
            this.txtTitleAnimate.textSize = this.context.sp(hintSize).toFloat()
        }
        if (hintColor != -1) {
            this.edtInput.setHintTextColor(this.context.resColor(hintColor))
            this.txtTitle.setTextColor(this.context.resColor(hintColor))
            this.txtTitleAnimate.setTextColor(this.context.resColor(hintColor))
        }
        if (hintFontFamily != -1) {
            this.txtTitle.typeface = ResourcesCompat.getFont(context, hintFontFamily)
            this.txtTitleAnimate.typeface = ResourcesCompat.getFont(context, hintFontFamily)
        }
        this.txtTitle.text = hint?:""
        this.txtTitleAnimate.text = hint?:""
        setHint()
    }

    private fun setHint() {
        if (!text.isNullOrEmpty()) {
            hintShowAnimation()
        } else {
            hintHideAnimation()
        }
    }

    private fun initEdittext() {

        if (textSize != -1) {
            this.edtInput.textSize = this.context.sp(textSize).toFloat()
        }

        if (textColor != -1) {
            this.edtInput.setTextColor(this.context.resColor(textColor))
        }

        if (textFontFamily != -1) {
            this.edtInput.typeface = ResourcesCompat.getFont(context, textFontFamily)
        }

        if (textMaxLength != -1) {
            this.edtInput.setMaxLength(textMaxLength)
        }

        this.edtInput.onTextChanged { charSequence, i, i2, i3 ->
            text = charSequence.toString()
            setHint()
            setCounter()
        }

        text.notNull { this.edtInput.setText(it) }
    }

    private fun initLine() {
        this.bottomLine.visibleIf(hasBottomLine, true)
    }

    private fun initCounter() {
        if (textMaxLength == -1 && hasCounter) {
            throw Exception("max length must not be null")
        } else {
            txtCounter.visibleIf(hasCounter)
            setCounter()
        }
    }

    private fun setCounter() {
        txtCounter.text = "${text?.length ?: 0}/${textMaxLength}"
    }

    private fun hintShowAnimation() {
        if (this.txtTitleAnimate.visibility == View.INVISIBLE) {
            val startY: Float = this.edtInput.y
            val endY: Float = this.txtTitle.y
            val translateY = ObjectAnimator.ofFloat(
                this.txtTitleAnimate, "translationY",
                startY,
                endY
            )

            val startX: Float = this.edtInput.x
            val endX: Float = this.txtTitle.x
            val translateX = ObjectAnimator.ofFloat(
                this.txtTitleAnimate, "translationX",
                startX,
                endX
            )

            val startSize: Float = this.context.px2sp(this.edtInput.textSize.toInt())
            val endSize: Float = this.context.px2sp(this.txtTitle.textSize.toInt())
            val size = ValueAnimator.ofFloat(
                startSize,
                endSize
            )
            size.addUpdateListener {
                this.txtTitleAnimate.textSize = (it.animatedValue as Float)
            }

            val startColor: Int = this.edtInput.currentHintTextColor
            val endColor: Int = this.txtTitle.currentTextColor
            val color = ValueAnimator()
            color.setIntValues(startColor, endColor);
            color.setEvaluator(ArgbEvaluator())
            color.addUpdateListener {
                this.txtTitleAnimate.setTextColor(it.animatedValue as Int)
            }

            showAnimator.duration = 300
            showAnimator.addListener(
                object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        //Reset position
                        this@CustomTextInputlayout.edtInput.hint = ""
                    }

                    override fun onAnimationCancel(animation: Animator?) {

                    }

                    override fun onAnimationStart(animation: Animator?) {
                        this@CustomTextInputlayout.txtTitleAnimate.visible()
                    }
                }
            )
            showAnimator.playTogether(translateX, translateY, size,color)
            showAnimator.start()
        }
    }

    private fun hintHideAnimation() {
        if (this.txtTitleAnimate.visibility != View.INVISIBLE) {
            val startY: Float = this.txtTitle.y
            val endY: Float = this.edtInput.y
            val translateY = ObjectAnimator.ofFloat(
                this.txtTitleAnimate, "translationY",
                startY,
                endY
            )

            val startX: Float = this.txtTitle.x
            val endX: Float = this.edtInput.x
            val translateX = ObjectAnimator.ofFloat(
                this.txtTitleAnimate, "translationX",
                startX,
                endX
            )

            val startSize: Float = this.context.px2sp(this.txtTitle.textSize.toInt())
            val endSize: Float = this.context.px2sp(this.edtInput.textSize.toInt())
            val size = ValueAnimator.ofFloat(
                startSize,
                endSize
            )
            size.addUpdateListener {
                this.txtTitleAnimate.textSize = (it.animatedValue as Float)
            }

            val startColor: Int =  this.txtTitle.currentTextColor
            val endColor: Int =this.edtInput.currentHintTextColor
            val color = ValueAnimator()
            color.setIntValues(startColor, endColor);
            color.setEvaluator(ArgbEvaluator())
            color.addUpdateListener {
                this.txtTitleAnimate.setTextColor(it.animatedValue as Int)
            }

            hideAnimator.duration = 300
            hideAnimator.addListener(
                object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        this@CustomTextInputlayout.edtInput.hint =
                            this@CustomTextInputlayout.txtTitle.text
                        //Reset position
                        this@CustomTextInputlayout.txtTitleAnimate.invisible()
                        this@CustomTextInputlayout.txtTitleAnimate.run {
                            translationX = 0f
                            translationY = 0f
                            textSize = startSize
                            setTextColor(startColor)
                        }
                    }

                    override fun onAnimationCancel(animation: Animator?) {

                    }

                    override fun onAnimationStart(animation: Animator?) {
                    }
                }
            )
            hideAnimator.playTogether(translateX, translateY, size,color)
            hideAnimator.start()
        }
    }
}