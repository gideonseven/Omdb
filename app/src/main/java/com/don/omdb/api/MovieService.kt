package com.don.omdb.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by gideon on 02,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
interface MovieService {
    /**
     * GET-------------------------------------------------------------------------------------------------------------------------
     */

    @GET(".")
    fun getMoviesList(
        @Query("apikey") apiKey: String,
        @Query("s") keyword: String,
        @Query("type") type: String,
        @Query("page") page: Int
    ): Call<JsonObject>


    @GET(".")
    fun getDetailMovie(
        @Query("apikey") apiKey: String,
        @Query("i") imdbID: String
    ): Call<JsonObject>
}

