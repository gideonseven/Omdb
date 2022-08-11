package com.don.omdb.utils

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.operators.ApiResponseSuspendOperator
import okhttp3.Response
import timber.log.Timber


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
class NetworkResponseOperator<Result, RC> constructor(
    private val converter: (Result?) -> RC?,
    private val success: suspend (Pair<Response, RC?>) -> Unit,
    private val fail: suspend (SimpleNetworkModel) -> Unit,
    private val noAuth: suspend () -> Unit
) : ApiResponseSuspendOperator<Result>() {

    override suspend fun onSuccess(apiResponse: ApiResponse.Success<Result>) {
        val response = converter(apiResponse.data)
        success(Pair(apiResponse.raw, response))
    }

    override suspend fun onError(apiResponse: ApiResponse.Failure.Error<Result>) {
        Timber.e("=== NetworkResponseOperator onError ${apiResponse.statusCode}")
        Timber.e("=== NetworkResponseOperator onError ${apiResponse.statusCode.code}")

        when (apiResponse.statusCode) {
            StatusCode.Unauthorized,
            StatusCode.Forbidden -> {
                noAuth()
            }

            StatusCode.ServiceUnavailable,
            StatusCode.NotFound -> {
                fail(
                    SimpleNetworkModel(
                        StatusCode.NotFound.name,
                        apiResponse.response.message()
                    )
                )
            }

            else -> fail(
                SimpleNetworkModel(
                    Api.RequestStatus.FAIL.status,
                    apiResponse.response.message()
                )
            )
        }
    }

    override suspend fun onException(apiResponse: ApiResponse.Failure.Exception<Result>) {
        fail(
            SimpleNetworkModel(
                Api.RequestStatus.FAIL.status,
                apiResponse.message
            )
        )
    }
}