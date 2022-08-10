package com.don.omdb.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
sealed class ApiException : Throwable() {
    object NotAuth : ApiException()
    data class Fail(val result: SimpleNetworkModel) : ApiException()
    data class Maintenance(override val message: String?) : ApiException()
    data class ObsoleteApp(override val message: String?) : ApiException()
}

fun <Type : CoreRequestType, Result> Flow<ResponseState<Type, Result>>.handleErrors(type: Type): Flow<ResponseState<Type, Result>> {
    return this.catch { e ->
        when (e) {
            is ApiException.Maintenance -> emit(ResponseState.Maintenance(e.message))
            is ApiException.ObsoleteApp -> emit(ResponseState.ObsoleteApp(e.message))
            is ApiException.NotAuth -> emit(ResponseState.NoAuth(type))
            is ApiException.Fail -> emit(ResponseState.Fail(type, e.result))
            else -> emit(
                ResponseState.Fail(
                    type,
                    errorModel = SimpleNetworkModel(message = e.message)
                )
            )
        }
    }
}