package com.musasyihab.stuffwelike.di.module

import android.app.Activity
import com.musasyihab.stuffwelike.ui.selection.SelectionContract
import com.musasyihab.stuffwelike.ui.selection.SelectionPresenter
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


}