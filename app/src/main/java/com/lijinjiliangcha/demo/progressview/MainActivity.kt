package com.lijinjiliangcha.demo.progressview

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val anim: ValueAnimator by lazy {
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.setDuration(3000L)
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener(animListener)
        animator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListener()
    }

    private fun initListener() {
        btn.setOnClickListener {
            val pro = edt.text.toString()
            if (pro.isBlank())
                return@setOnClickListener
            progressView.progress = pro.toFloat()
        }

        btn_anim.setOnClickListener {
            if (anim.isStarted)
                return@setOnClickListener
            anim.start()
        }
    }

    private fun initAnim() {

    }

    private val animListener = ValueAnimator.AnimatorUpdateListener {
        val value: Float = it.animatedValue as Float
        progressView.progress = value
    }
}
