package com.don.omdb.usecase

import com.don.omdb.data.remote.UnsplashItem
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
        page: Int,
        perPage: Int,
        orderBy: String
    ): Flow<ResponseState<RequestType, List<UnsplashItem>?>>
}