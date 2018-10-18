package com.musasyihab.stuffwelike.di.module

import android.app.Application
import com.musasyihab.stuffwelike.BaseApp
import com.musasyihab.stuffwelike.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}