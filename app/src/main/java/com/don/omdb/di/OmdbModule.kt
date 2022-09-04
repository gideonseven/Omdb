package com.don.omdb.di

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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by gideon on 02,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
@Module
class OmdbModule {

    lateinit var androidNiddler: AndroidNiddler

    @Provides
    internal fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NiddlerOkHttpInterceptor(androidNiddler, "DEFAULT"))
            .build()
    }

    @Provides
    internal fun gson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .create()
    }

    @Provides
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(JniHelper.baseUrl())
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .client(providesOkHttpClient())
            .build()
    }

    @Provides
    internal fun provideMovieService(): MovieService {
        return provideRetrofit().create(MovieService::class.java)
    }

    @Provides
    fun provideRepository(): OmdbRepository {
        val remoteRepository = RemoteRepository()
        return OmdbRepository.getInstance(remoteRepository)!!
    }
}