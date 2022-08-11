package com.don.omdb.data.repository

import com.don.omdb.api.OmdbApi
import com.don.omdb.data.remote.movies.Movies
import com.don.omdb.repository.IOmdbRepository
import com.don.omdb.utils.RequestType
import com.don.omdb.utils.ResponseState
import com.don.omdb.utils.getResult
import com.don.omdb.utils.succeedMapper
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
@BindType(installIn = BindType.Component.VIEW_MODEL)
class OmdbRepositoryImpl @Inject constructor(
    private val api: OmdbApi
) : IOmdbRepository {

    override fun getMovies(
        requestType: RequestType,
        page: Int,
        perPage: Int,
        orderBy: String
    ) = getResult(
        requestType,
        call = { api.getMoviesList(page = page, per_page = perPage, order_by = orderBy) },
        converter = { it }
    ).succeedMapper {
        it?.second
    }

    override fun getMovies(
        requestType: RequestType,
        page: Int
    ): Flow<ResponseState<RequestType, Movies?>> = getResult(
        requestType,
        call = { api.getMoviesList(page = page) },
        converter = { it }
    ).succeedMapper {
        it?.second
    }

    override fun getSearchMovies(
        requestType: RequestType,
        query: String,
        page: Int
    ): Flow<ResponseState<RequestType, Movies?>> = getResult(
        requestType,
        call = { api.getSearchMovie(page = page, query = query) },
        converter = { it }
    ).succeedMapper {
        it?.second
    }
}