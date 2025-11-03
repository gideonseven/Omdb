package com.don.omdb

import android.app.Application
import com.chimerapps.niddler.core.AndroidNiddler
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
@HiltAndroidApp
class MovieApp : Application() {

    @Inject
    lateinit var niddler: AndroidNiddler

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        // Attach Niddler once Hilt injected it
        niddler.attachToApplication(this)
    }
}