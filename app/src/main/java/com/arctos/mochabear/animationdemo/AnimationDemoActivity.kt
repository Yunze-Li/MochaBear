package com.arctos.mochabear.animationdemo

import android.animation.FloatEvaluator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.arctos.mochabear.R

class AnimationDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_demo)

        val linearInterpolatorButton =
            findViewById<AppCompatButton>(R.id.linear_interpolator_button)
        linearInterpolatorButton.setOnClickListener {
            val evaluator = object : FloatEvaluator() {
                override fun evaluate(
                    fraction: Float,
                    startValue: Number?,
                    endValue: Number?
                ): Float {
                    val currentValue =
                        startValue!!.toFloat() + fraction * (endValue!!.toFloat() - startValue!!.toFloat())
                    return if (currentValue > 180.0f) {
                        -currentValue
                    } else {
                        currentValue
                    }
                }
            }
            val animation = ObjectAnimator.ofObject(it, "rotation", evaluator, 0.0f, 360.0f)
            animation.duration = 2000
            animation.interpolator = AccelerateDecelerateInterpolator()
            animation.start()
        }
    }
}