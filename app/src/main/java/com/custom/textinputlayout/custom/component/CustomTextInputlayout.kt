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

    var attr = AttributeData()

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
        this.txtTitle.text = attr.hint ?: ""
        this.txtTitleAnimate.text = attr.hint ?: ""
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
            attr.text = charSequence.toString()
            startHintAnimation()
            setCounter()
        }

        attr.text.notNull { this.edtInput.setText(it) }
    }

    private fun initLine() {
        this.bottomLine.visibleIf(attr.hasBottomLine, true)
    }

    private fun initCounter() {
        if (attr.textMaxLength == -1 && attr.hasCounter) {
            throw Exception("max length must not be null")
        } else {
            txtCounter.visibleIf(attr.hasCounter)
            setCounter()
        }
    }

    private fun setCounter() {
        txtCounter.text = "${attr.text?.length ?: 0}/${attr.textMaxLength}"
    }

    private fun startHintAnimation() {
        if (!attr.text.isNullOrEmpty()) {
            hintShowAnimation()
        } else {
            hintHideAnimation()
        }
    }

    private fun hintShowAnimation() {
        if (this.txtTitleAnimate.visibility == View.GONE) {
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
            showAnimator.playTogether(translateX, translateY, size, color)
            showAnimator.start()
        }
    }

    private fun hintHideAnimation() {
        if (this.txtTitleAnimate.visibility != View.GONE) {
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

            val startColor: Int = this.txtTitle.currentTextColor
            val endColor: Int = this.edtInput.currentHintTextColor
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
                        this@CustomTextInputlayout.txtTitleAnimate.gone()
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
            hideAnimator.playTogether(translateX, translateY, size, color)
            hideAnimator.start()
        }
    }

}