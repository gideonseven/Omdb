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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by gideon on 02,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
@Module
class OmdbModule {

    @Provides
    internal fun provideLoggingCapableHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()

        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    internal fun gson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .create()
    }

    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(JniHelper.baseUrl())
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .client(okHttpClient)
            .build()
    }

    @Provides
    internal fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create<MovieService>(MovieService::class.java)
    }

    @Provides
    fun provideRepository(): OmdbRepository {
        val remoteRepository = RemoteRepository()
        return OmdbRepository.getInstance(remoteRepository)!!
    }


}