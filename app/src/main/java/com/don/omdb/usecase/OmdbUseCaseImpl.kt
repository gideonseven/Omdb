package com.don.omdb.usecase

import com.don.omdb.repository.IOmdbRepository
import com.don.omdb.utils.RequestType
import com.don.omdb.utils.succeedMapper
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
@BindType(installIn = BindType.Component.VIEW_MODEL)
class OmdbUseCaseImpl @Inject constructor(
    private val repository: IOmdbRepository
) : IOmdbUseCase {

    override fun getMovies(
        requestType: RequestType,
        page: Int,
        perPage: Int,
        orderBy: String
    ) =
        repository.getMovies(requestType, page, perPage, orderBy)
            .succeedMapper {
                it
            }

    override fun getMovies(
        requestType: RequestType,
        page: Int
    ) = repository.getMovies(requestType, page)
        .succeedMapper {
            it
        }

    override fun getSearchMovies(
        requestType: RequestType,
        query: String,
        page: Int
    ) = repository.getSearchMovies(requestType, query, page)
        .succeedMapper {
            it
        }
}