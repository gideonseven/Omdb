package com.don.omdb.di

import android.app.Application
import com.chimerapps.niddler.core.AndroidNiddler
import com.chimerapps.niddler.interceptor.okhttp.NiddlerOkHttpInterceptor
import com.don.omdb.api.OmdbApi
import com.don.omdb.utils.JniHelper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by gideon on 02,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
@Module
@InstallIn(SingletonComponent::class)
object OmdbModule {

    @Singleton
    @Provides
    internal fun gson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl(JniHelper.baseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideNiddler(application: Application): NiddlerOkHttpInterceptor {
        val niddler = AndroidNiddler.Builder()
            // Use port 0 to prevent conflicting ports, auto-discovery will find it anyway!
            .setPort(0)
            // Set com.niddler.icon in AndroidManifest meta-data to an icon you wish to use for this session
            .setNiddlerInformation(AndroidNiddler.fromApplication(application))
            .setMaxStackTraceSize(10)
            .build()
        return NiddlerOkHttpInterceptor(niddler, "OMDB")
    }

    @Singleton
    @Provides
    fun providesBasicOkHttpClient(
        niddlerOkHttpInterceptor: NiddlerOkHttpInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .readTimeout(180, TimeUnit.SECONDS)
            .connectTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
            .addInterceptor(niddlerOkHttpInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesOmdbApi(retrofit: Retrofit, okHttpClient: OkHttpClient): OmdbApi {
        return  retrofit.newBuilder().client(okHttpClient).build()
            .create(OmdbApi::class.java)
    }
}