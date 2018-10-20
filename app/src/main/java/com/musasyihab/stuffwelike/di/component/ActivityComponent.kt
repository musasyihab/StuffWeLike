package com.musasyihab.stuffwelike.di.component

import com.musasyihab.stuffwelike.ui.selection.SelectionActivity
import com.musasyihab.stuffwelike.di.module.ActivityModule
import com.musasyihab.stuffwelike.ui.review.ReviewActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(selectionActivity: SelectionActivity)
    fun inject(reviewActivity: ReviewActivity)

}