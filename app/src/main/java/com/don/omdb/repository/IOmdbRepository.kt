package com.don.omdb.repository

import com.don.omdb.data.remote.MdlMovieList
import com.don.omdb.utils.Repository
import com.don.omdb.utils.RequestType
import com.don.omdb.utils.ResponseState
import kotlinx.coroutines.flow.Flow


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
interface IOmdbRepository : Repository {
    fun getMovies(
        requestType: RequestType,
        keyword: String,
        type: String,
        page: Int
    ): Flow<ResponseState<RequestType, MdlMovieList?>>
}