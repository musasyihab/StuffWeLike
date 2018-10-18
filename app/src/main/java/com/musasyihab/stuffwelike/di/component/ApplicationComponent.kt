package com.musasyihab.stuffwelike.di.component

import com.musasyihab.stuffwelike.BaseApp
import com.musasyihab.stuffwelike.di.module.ApplicationModule
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)

}