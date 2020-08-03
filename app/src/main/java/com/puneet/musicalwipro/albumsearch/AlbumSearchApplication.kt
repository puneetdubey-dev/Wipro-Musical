package com.puneet.musicalwipro.albumsearch

import android.app.Activity
import android.app.Application
import com.puneet.musicalwipro.albumsearch.di.component.AppComponent
import com.puneet.musicalwipro.albumsearch.di.component.DaggerAppComponent
import com.puneet.musicalwipro.albumsearch.di.modules.UtilsModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class AlbumSearchApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private lateinit var appComponent: AppComponent

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        createAppComponent()
    }

    private fun createAppComponent() {
        appComponent = DaggerAppComponent
            .builder()
            .utilsModule(UtilsModule(this, this))
            .build()

        appComponent.inject(this)
    }
}