package com.don.omdb

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
@HiltAndroidApp
class OmdbApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Logging
        Timber.plant(Timber.DebugTree())
    }
}