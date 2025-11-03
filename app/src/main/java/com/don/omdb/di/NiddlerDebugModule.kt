package com.don.omdb.di

import android.app.Application
import android.content.Context
import com.chimerapps.niddler.core.AndroidNiddler
import com.chimerapps.niddler.interceptor.okhttp.NiddlerOkHttpInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NiddlerDebugModule {

    @Provides
    @Singleton
    fun provideNiddler(@ApplicationContext context: Context): AndroidNiddler {
        return AndroidNiddler.Builder()
            .setPort(0) // let it pick a free port; auto-discovery works
            .setNiddlerInformation(AndroidNiddler.fromApplication(context as Application?))
            .setMaxStackTraceSize(10)
            .build()
    }

    @Provides
    @Singleton
    fun provideNiddlerInterceptor(niddler: AndroidNiddler): Interceptor {
        return NiddlerOkHttpInterceptor(niddler, "DEFAULT")
    }
}
