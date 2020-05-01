package com.custom.textinputlayout.custom.component

import android.animation.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.custom.textinputlayout.R
import com.custom.textinputlayout.extension.gone
import com.custom.textinputlayout.extension.invisible
import com.custom.textinputlayout.extension.px2sp
import com.custom.textinputlayout.extension.visible
import kotlinx.android.synthetic.main.component_textinputlayout.view.*

/**
 *   Created by mertttutsak on 26.04.2020.
 */

class HintAnimator(
    val viewGroup: ViewGroup,
    val hint: TextView,
    val animate: TextView,
    val edtInput: EditText,
    val isAnimateEnable: Boolean,
    val isAlignHintWithEdt: Boolean
) {
    enum class HINT_STATE { SHOW, HIDE }

    private val HINT_ANIMATOR_DURATION: Long = 300

    private var state: HINT_STATE? =null

    private var showAnimator: AnimatorSet
    private var hideAnimator: AnimatorSet

    init {
        if (!isAnimateEnable) {
            animate.gone()
        }
        showAnimator = AnimatorSet().apply {
            duration = HINT_ANIMATOR_DURATION
        }
        hideAnimator = AnimatorSet().apply {
            duration = HINT_ANIMATOR_DURATION
        }
    }

    private fun show() {
        if (!isAnimateEnable) {
            hint.visible()
            return
        }

        if (this.state != HINT_STATE.SHOW) {
            val startY: Float = this.edtInput.y + (this.edtInput.height)
            val endY: Float = this.hint.y
            val translateY = ObjectAnimator.ofFloat(
                this.animate, "translationY",
                startY,
                endY
            )

            val startX: Float = this.edtInput.x
            val endX: Float = this.hint.x
            val translateX = ObjectAnimator.ofFloat(
                this.animate, "translationX",
                startX,
                endX
            )
            val startSize: Float = this.viewGroup.context.px2sp(this.edtInput.textSize.toInt())
            val endSize: Float = this.viewGroup.context.px2sp(this.hint.textSize.toInt())
            val size = ValueAnimator.ofFloat(
                startSize,
                endSize
            )
            size.addUpdateListener {
                this.animate.textSize = (it.animatedValue as Float)
            }

            val startColor: Int = this.edtInput.currentHintTextColor
            val endColor: Int = this.hint.currentTextColor
            val color = ValueAnimator()
            color.setIntValues(startColor, endColor);
            color.setEvaluator(ArgbEvaluator())
            color.addUpdateListener {
                this.animate.setTextColor(it.animatedValue as Int)
            }

            showAnimator.addListener(
                object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        //Reset position
                        this@HintAnimator.edtInput.hint = ""
                    }

                    override fun onAnimationCancel(animation: Animator?) {

                    }

                    override fun onAnimationStart(animation: Animator?) {
                        this@HintAnimator.state = HINT_STATE.SHOW
                    }
                }
            )
            showAnimator.playTogether(translateX, translateY, size, color)
            showAnimator.start()
        }
    }

    private fun hide() {
        if (!isAnimateEnable) {
            hint.invisible()
            return
        }
        if (this.state != HINT_STATE.HIDE) {
            val startY: Float = this.hint.y
            val endY: Float = this.edtInput.y + (this.edtInput.height)
            val translateY = ObjectAnimator.ofFloat(
                this.animate, "translationY",
                startY,
                endY
            )

            val startX: Float = this.hint.x
            val endX: Float = this.edtInput.x
            val translateX = ObjectAnimator.ofFloat(
                this.animate, "translationX",
                startX,
                endX
            )

            val startSize: Float = this.viewGroup.context.px2sp(this.hint.textSize.toInt())
            val endSize: Float = this.viewGroup.context.px2sp(this.edtInput.textSize.toInt())
            val size = ValueAnimator.ofFloat(
                startSize,
                endSize
            )
            size.addUpdateListener {
                this.animate.textSize = (it.animatedValue as Float)
            }

            val startColor: Int = this.hint.currentTextColor
            val endColor: Int = this.edtInput.currentHintTextColor
            val color = ValueAnimator()
            color.setIntValues(startColor, endColor);
            color.setEvaluator(ArgbEvaluator())
            color.addUpdateListener {
                this.animate.setTextColor(it.animatedValue as Int)
            }

            hideAnimator.addListener(
                object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        //Reset position
                        /*this@HintAnimator.animate.run {
                            gone()
                            translationX = 0f
                            translationY = 0f
                            textSize = startSize
                            setTextColor(startColor)
                        }*/
                    }

                    override fun onAnimationCancel(animation: Animator?) {

                    }

                    override fun onAnimationStart(animation: Animator?) {
                        this@HintAnimator.state = HINT_STATE.HIDE
                    }
                }
            )
            hideAnimator.playTogether(translateX, translateY, size, color)
            hideAnimator.start()
        }
    }

    fun showIf(isShow: Boolean) {
        this.hintAlign()
        if (isShow) {
            show()
        } else {
            hide()
        }
    }

    fun hintAlign() {
        if (isAlignHintWithEdt) {
            hint.x = edtInput.x
            animate.x = edtInput.x
        }
    }
}