package com.don.omdb.data.repository

import com.don.omdb.api.OmdbApi
import com.don.omdb.repository.IOmdbRepository
import com.don.omdb.utils.*
import com.paulrybitskyi.hiltbinder.BindType
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
}