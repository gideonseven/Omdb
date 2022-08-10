package com.don.omdb.usecase

import com.don.omdb.data.remote.MdlMovieList
import com.don.omdb.utils.RequestType
import com.don.omdb.utils.ResponseState
import kotlinx.coroutines.flow.Flow


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */

interface IOmdbUseCase {
    fun getMovies(
        requestType: RequestType,
        keyword: String,
        type: String,
        page: Int
    ): Flow<ResponseState<RequestType, MdlMovieList?>>
}