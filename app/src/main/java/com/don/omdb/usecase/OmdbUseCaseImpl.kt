package com.don.omdb.usecase

import com.don.omdb.repository.IOmdbRepository
import com.don.omdb.utils.RequestType
import com.don.omdb.utils.handleErrors
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
        keyword: String,
        type: String,
        page: Int
    ) =
        repository.getMovies(requestType, keyword, type, page).handleErrors(requestType)
            .succeedMapper {
                it
            }

}