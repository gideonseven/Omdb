package com.don.omdb.api

import com.don.omdb.data.remote.MdlDetail
import com.don.omdb.data.remote.MdlMovieList
import com.don.omdb.utils.JniHelper
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by gideon on 02,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
interface OmdbApi {
    /**
     * GET-------------------------------------------------------------------------------------------------------------------------
     */

    @GET(".")
    fun getMoviesList(
        @Query("apikey") apiKey: String = JniHelper.apiKey(),
        @Query("s") keyword: String,
        @Query("type") type: String,
        @Query("page") page: Int
    ): ApiResponse<MdlMovieList>


    @GET(".")
    fun getDetailMovie(
        @Query("apikey") apiKey: String,
        @Query("i") imdbID: String?
    ): ApiResponse<MdlDetail>
}

