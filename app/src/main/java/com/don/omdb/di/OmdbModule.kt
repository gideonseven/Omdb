package com.don.omdb.di

import com.don.omdb.BuildConfig
import com.don.omdb.api.MovieService
import com.don.omdb.data.OmdbRepository
import com.don.omdb.data.remote.RemoteRepository
import com.don.omdb.utils.JniHelper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        // Add logging interceptor for debug builds only
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            builder.addInterceptor(loggingInterceptor)
        }

        return builder.build()
    }

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