package com.puneet.musicalwipro.albumsearch.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.puneet.musicalwipro.albumsearch.AlbumSearchApplication
import com.puneet.musicalwipro.albumsearch.R
import com.puneet.musicalwipro.albumsearch.repository.AlbumSearch
import com.puneet.musicalwipro.albumsearch.repository.AlbumSearchImpl
import com.puneet.musicalwipro.albumsearch.retrofit.SearchApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val PREF_FILE = "lastfmsearch.prefs"

@Module
class UtilsModule(
    private val context: Context,
    private val application: Application
) {

    @Provides
    fun providesApplication(): Application {
        return application
    }

    @Provides
    fun providesLastFMSearchApplication(): AlbumSearchApplication {
        return application as AlbumSearchApplication
    }

    @Provides
    fun providesContext(): Context {
        return context
    }

    @Provides
    fun providesResources(): Resources {
        return context.resources
    }

    @Singleton
    @Provides
    fun providesSharedPref(): SharedPreferences {
        return context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideSearchApiService(okHttpClient: OkHttpClient): SearchApiService {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(context.getString(R.string.search_api_base_url))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(SearchApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchManager(searchApiService: SearchApiService): AlbumSearch {
        return AlbumSearchImpl(
            searchApiService,
            context
        )
    }
}