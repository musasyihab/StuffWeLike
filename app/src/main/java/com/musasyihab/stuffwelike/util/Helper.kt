package com.musasyihab.stuffwelike.util

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import com.musasyihab.stuffwelike.R

object Helper {
    private const val DURATION_SHORT: Long = 600
    private const val DURATION_LONG: Long = 1000

    fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        val height = view.height.toFloat()

        val animate = TranslateAnimation(
                0f,
                0f,
                height,
                0f)
        animate.duration = DURATION_LONG
        animate.fillAfter = true

        view.startAnimation(animate)
    }

    fun fadeOut(view: View) {

        if(view.visibility == View.VISIBLE) {
            val animation = AnimationUtils.loadAnimation(view.context, R.anim.fade_out)
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {
                    // nothing to do
                }

                override fun onAnimationEnd(p0: Animation?) {
                    view.visibility = View.GONE
                }

                override fun onAnimationStart(p0: Animation?) {

                }
            })
            animation.duration = DURATION_SHORT
            animation.fillAfter = true

            view.startAnimation(animation)
        }
    }

    fun fadeIn(view: View) {
        view.visibility = View.INVISIBLE
        val animation = AnimationUtils.loadAnimation(view.context, R.anim.fade_in)
        animation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {
                // nothing to do
            }

            override fun onAnimationEnd(p0: Animation?) {
                view.visibility = View.VISIBLE
            }

            override fun onAnimationStart(p0: Animation?) {
                view.visibility = View.INVISIBLE
            }
        })
        animation.duration = DURATION_SHORT
        animation.fillAfter = true

        view.startAnimation(animation)
    }

}