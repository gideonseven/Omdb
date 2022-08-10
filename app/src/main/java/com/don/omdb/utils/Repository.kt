package com.don.omdb.utils

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOperator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */


interface Repository {
// to do default stuff
}

fun <Type : CoreRequestType, Result, ResultConverter> getResult(
    type: Type,
    call: suspend () -> ApiResponse<Result>,
    converter: (Result?) -> ResultConverter?,
) = flow {
    call().suspendOperator(
        NetworkResponseOperator(
            success = { r ->
                emit(ResponseState.Success(type, r))
            },
            fail = {
                throw ApiException.Fail(it)
            },
            noAuth = {
                throw ApiException.NotAuth
            },
            obsoleteApp = {
                throw ApiException.ObsoleteApp(it)
            },
            maintenance = {
                throw ApiException.Maintenance(it)
            },
            converter = {
                converter(it)
            }
        )
    )
}.flowOn(Dispatchers.IO)
