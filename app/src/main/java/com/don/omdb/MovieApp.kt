package com.don.omdb

import android.app.Application
import android.content.Context
import com.don.omdb.di.DaggerOmdbComponent
import com.don.omdb.di.OmdbComponent
import com.don.omdb.di.OmdbModule
import timber.log.Timber

/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
class MovieApp : Application() {
    companion object {
        var appContext: Context? = null
    }

    lateinit var appComponent: OmdbComponent

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext


        // Logging
        Timber.plant(Timber.DebugTree())

        // Set up Dagger
        createAppComponent()

    }

    private fun createAppComponent() {
        appComponent = DaggerOmdbComponent.builder()
                .omdbModule(OmdbModule())
                .build()
    }


}