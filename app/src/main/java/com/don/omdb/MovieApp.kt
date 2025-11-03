package com.don.omdb

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
@HiltAndroidApp
class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}