package com.don.omdb.data.repository

import com.don.omdb.api.OmdbApi
import com.don.omdb.repository.IOmdbRepository
import com.don.omdb.utils.RequestType
import com.don.omdb.utils.getResult
import com.don.omdb.utils.succeedMapper
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
        keyword: String,
        type: String,
        page: Int
    ) = getResult(
        requestType,
        call = { api.getMoviesList(keyword = keyword, type = type, page = page) },
        converter = { it }
    ).succeedMapper {
        it?.second
    }
}