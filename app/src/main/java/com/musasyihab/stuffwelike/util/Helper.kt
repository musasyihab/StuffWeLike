package com.musasyihab.stuffwelike.util

import android.view.View
import android.view.animation.TranslateAnimation

object Helper {
    private val SLIDE_DURATION: Long = 1500

    fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        val height = view.height.toFloat()

        val animate = TranslateAnimation(
                0f,
                0f,
                height,
                0f)
        animate.duration = SLIDE_DURATION
        animate.fillAfter = true

        view.startAnimation(animate)
    }
}