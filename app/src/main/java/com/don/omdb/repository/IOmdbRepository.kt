package com.don.omdb.repository

import com.don.omdb.data.remote.UnsplashItem
import com.don.omdb.data.remote.movies.Movies
import com.don.omdb.utils.Repository
import com.don.omdb.utils.RequestType
import com.don.omdb.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
interface IOmdbRepository : Repository {
    fun getMovies(
        requestType: RequestType,
        page: Int,
        perPage: Int,
        orderBy: String
    ): Flow<ResponseState<RequestType, List<UnsplashItem>?>>


    fun getMovies( requestType: RequestType,page: Int
    ): Flow<ResponseState<RequestType, Movies?>>
}