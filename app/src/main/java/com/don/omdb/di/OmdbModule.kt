package com.don.omdb.di

import android.app.Application
import com.chimerapps.niddler.core.AndroidNiddler
import com.chimerapps.niddler.interceptor.okhttp.NiddlerOkHttpInterceptor
import com.don.omdb.api.OmdbApi
import com.don.omdb.utils.Api
import com.don.omdb.utils.JniHelper
import com.don.omdb.utils.OkHttpDefault
import com.don.omdb.utils.OkHttpWithAuth
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
//import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
/**
 * Created by gideon on 02,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
@Module
@InstallIn(SingletonComponent::class)
object OmdbModule {
/*

    @Singleton
    @Provides
    internal fun gson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .create()
    }
*/

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder().baseUrl(JniHelper.baseUrl())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
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
    @OkHttpDefault
    @Provides
    fun providesBasicOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .readTimeout(180, TimeUnit.SECONDS)
            .connectTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @OkHttpWithAuth
    @Singleton
    fun providesOkHttpWithAuthHeader(
        @OkHttpDefault okHttpClient: OkHttpClient
    ): OkHttpClient {
        return okHttpClient.newBuilder().addInterceptor { chain ->
            var request = chain.request()
            request = request.newBuilder()
                .header(
                    Api.AUTH_HEADER,
                    "${Api.CLIENT_ID} ${JniHelper.apiKey()}"
                ).build()
            chain.proceed(request)
        }.build()
    }

   /* @Singleton
    @Provides
    fun providesOmdbApi(retrofit: Retrofit, @OkHttpWithAuth okHttpClient: OkHttpClient): OmdbApi {
        return  retrofit.newBuilder().client(okHttpClient).build()
            .create(OmdbApi::class.java)
    }*/

    @Singleton
    @Provides
    fun providesOmdbApi(retrofit: Retrofit, @OkHttpDefault okHttpClient: OkHttpClient): OmdbApi {
        return  retrofit.newBuilder().client(okHttpClient).build()
            .create(OmdbApi::class.java)
    }
}