package com.musasyihab.stuffwelike.di.module

import android.app.Activity
import com.musasyihab.stuffwelike.ui.review.ReviewContract
import com.musasyihab.stuffwelike.ui.review.ReviewPresenter
import com.musasyihab.stuffwelike.ui.selection.SelectionContract
import com.musasyihab.stuffwelike.ui.selection.SelectionPresenter
import com.musasyihab.stuffwelike.ui.start.StartContract
import com.musasyihab.stuffwelike.ui.start.StartPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun provideSelectionPresenter(): SelectionContract.Presenter {
        return SelectionPresenter()
    }

    @Provides
    fun provideReviewPresenter(): ReviewContract.Presenter {
        return ReviewPresenter()
    }

    @Provides
    fun provideStartPresenter(): StartContract.Presenter {
        return StartPresenter()
    }


}