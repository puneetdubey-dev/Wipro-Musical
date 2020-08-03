package com.puneet.musicalwipro.albumsearch.di.modules

import com.puneet.musicalwipro.albumsearch.ui.DetailsFragment
import com.puneet.musicalwipro.albumsearch.ui.MusicAlbumActivity
import com.puneet.musicalwipro.albumsearch.ui.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class AppModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivityInjector(): MusicAlbumActivity

    @ContributesAndroidInjector
    abstract fun contributeSearchFragmentInjector(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailsFragmentInjector(): DetailsFragment

}
