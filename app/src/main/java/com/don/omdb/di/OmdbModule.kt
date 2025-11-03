package com.don.omdb.di

import android.app.Application
import android.content.Context
import com.chimerapps.niddler.core.AndroidNiddler
import com.chimerapps.niddler.interceptor.okhttp.NiddlerOkHttpInterceptor
import com.don.omdb.api.MovieService
import com.don.omdb.data.OmdbRepository
import com.don.omdb.data.remote.RemoteRepository
import com.don.omdb.utils.JniHelper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by gideon on 02,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
@Module
@InstallIn(SingletonComponent::class)
object OmdbModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(niddlerInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(niddlerInterceptor) // real in debug, no-op in release
            .build()

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .serializeNulls()
            .create()

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(JniHelper.baseUrl())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @Provides
    @Singleton
    fun provideRepository(): OmdbRepository {
        val remoteRepository = RemoteRepository()
        return OmdbRepository.getInstance(remoteRepository)
            ?: error("OmdbRepository.getInstance returned null")
    }
}