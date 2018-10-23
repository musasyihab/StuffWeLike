package com.musasyihab.stuffwelike.util

import android.support.annotation.VisibleForTesting
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

    @VisibleForTesting
    fun getAnimationListener(show: Boolean, view: View): Animation.AnimationListener {
        return object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                if (show) view.visibility = View.INVISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {
                if (show) view.visibility = View.VISIBLE
                else view.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        }
    }

    fun fadeOut(view: View) {

        if(view.visibility == View.VISIBLE) {
            val animation = AnimationUtils.loadAnimation(view.context, R.anim.fade_out)
            val listener = getAnimationListener(false, view)
            animation?.setAnimationListener(listener)
            animation?.duration = DURATION_SHORT
            animation?.fillAfter = true

            view.startAnimation(animation)
        }
    }

    fun fadeIn(view: View) {
        view.visibility = View.INVISIBLE
        val animation = AnimationUtils.loadAnimation(view.context, R.anim.fade_in)
        val listener = getAnimationListener(true, view)
        animation?.setAnimationListener(listener)
        animation?.duration = DURATION_SHORT
        animation?.fillAfter = true

        view.startAnimation(animation)
    }

}