package com.don.omdb

import android.app.Application
import android.content.Context
import com.chimerapps.niddler.core.AndroidNiddler
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

        val niddler = AndroidNiddler.Builder()
            .setPort(0) //Use port 0 to prevent conflicting ports, auto-discovery will find it anyway!
            .setNiddlerInformation(AndroidNiddler.fromApplication(this)) //Set com.niddler.icon in AndroidManifest meta-data to an icon you wish to use for this session
            .setMaxStackTraceSize(10)
            .build()
        niddler.attachToApplication(this)


        // Set up Dagger
        initDaggerModule(niddler)
    }

    private fun initDaggerModule(mNiddler: AndroidNiddler) {
        val omdbModule = OmdbModule()
        omdbModule.androidNiddler = mNiddler
        appComponent =
            DaggerOmdbComponent
                .builder()
                .omdbModule(omdbModule).build()
    }
}