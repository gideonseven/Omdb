package com.don.omdb.api

import com.don.omdb.data.remote.MdlDetail
import com.don.omdb.data.remote.UnsplashItem
import com.don.omdb.utils.Api
import com.don.omdb.utils.JniHelper
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.*

/**
 * Created by gideon on 02,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
interface OmdbApi {
    /**
     * @param page Page number to retrieve. (Optional; default: 1)
     * @param per_page Number of items per page. (Optional; default: 10)
     * @param order_by How to sort the photos. Optional. (Valid values: latest, oldest, popular; default: latest)
     */

    @GET("photos/")
    suspend fun getMoviesList(
        @Header(Api.AUTH_HEADER) token: String = JniHelper.apiKey(),
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("order_by") order_by: String,
    ): ApiResponse<List<UnsplashItem>>

    @GET(".")
    suspend fun getDetailMovie(
        @Query("apikey") apiKey: String,
        @Query("i") imdbID: String?
    ): ApiResponse<MdlDetail>
}