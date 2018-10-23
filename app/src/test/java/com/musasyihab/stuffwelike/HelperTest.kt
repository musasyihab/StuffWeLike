package com.musasyihab.stuffwelike

import android.view.View
import android.view.animation.Animation
import com.musasyihab.stuffwelike.util.Helper
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*

class HelperTest {
    @Test fun Helper_slideUp() {
        val mockedView = Mockito.mock(View::class.java)
        Helper.slideUp(mockedView)

        Mockito.verify(mockedView).visibility = View.VISIBLE
        Mockito.verify(mockedView, atLeastOnce()).startAnimation(ArgumentMatchers.any())
    }

    @Test fun Helper_fadeOut() {
        val mockedView = Mockito.mock(View::class.java)
        mockedView.visibility = View.VISIBLE
        val listener: Animation.AnimationListener = Helper.getAnimationListener(false, mockedView)
        listener.onAnimationEnd(null)
        Helper.fadeOut(mockedView)

        Mockito.verify(mockedView).visibility = View.GONE
        Mockito.verify(mockedView, atLeastOnce()).startAnimation(ArgumentMatchers.any())
    }

    @Test fun Helper_fadeIn() {
        val mockedView = Mockito.mock(View::class.java)
        val listener: Animation.AnimationListener = Helper.getAnimationListener(true, mockedView)
        listener.onAnimationEnd(null)
        Helper.fadeIn(mockedView)

        Mockito.verify(mockedView).visibility = View.VISIBLE
        Mockito.verify(mockedView, atLeastOnce()).startAnimation(ArgumentMatchers.any())
    }
}