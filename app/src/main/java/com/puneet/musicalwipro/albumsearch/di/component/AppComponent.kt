package com.puneet.musicalwipro.albumsearch.di.component

import com.puneet.musicalwipro.albumsearch.AlbumSearchApplication
import com.puneet.musicalwipro.albumsearch.di.modules.AppModule
import com.puneet.musicalwipro.albumsearch.di.modules.NetworkModule
import com.puneet.musicalwipro.albumsearch.di.modules.UtilsModule
import com.puneet.musicalwipro.albumsearch.di.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        NetworkModule::class,
        AppModule::class,
        UtilsModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent : AndroidInjector<AlbumSearchApplication> {

}