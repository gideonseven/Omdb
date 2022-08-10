package com.don.omdb.utils

import com.don.omdb.data.remote.UnsplashItem
import com.google.gson.Gson
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.operators.ApiResponseSuspendOperator
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    private val noAuth: suspend () -> Unit,
    private val obsoleteApp: suspend (String?) -> Unit,
    private val maintenance: suspend (String?) -> Unit,
) : ApiResponseSuspendOperator<Result>() {

    override suspend fun onSuccess(apiResponse: ApiResponse.Success<Result>) {
        val response = converter(apiResponse.data)

        var status: String? = Constants.TEXT_BLANK
        var message: String? = Constants.TEXT_BLANK
        var token: String? = Constants.TEXT_BLANK

        if (response is BaseNetworkModel) {
            status = response.status
            message = response.message
            token = response.token
            Timber.e("====  BASE NETWORK")

        } else if (response is String) {
          /*  Timber.e("====  STRING")
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val adapter: JsonAdapter<SimpleNetworkModel> =
                moshi.adapter(SimpleNetworkModel::class.java)
            val model = adapter.fromJson(response)*/
        } else {
            Timber.e("====  else ")

        }

        when {
            // TODO("Handle Api.RequestStatus.BLACKLIST")
            message == Api.RequestStatus.BLACKLIST.status -> {
                Timber.e(Api.RequestStatus.BLACKLIST.status)
            }

            status == Api.RequestStatus.MAINTENANCE.status -> {
                maintenance(message)
            }

            status == Api.RequestStatus.OBSOLETE.status -> {
                obsoleteApp(message)
            }

            status == Api.RequestStatus.FAIL.status -> {
                fail(
                    SimpleNetworkModel(
                        status, message, token, if (response is SimpleNetworkModel) {
                            response.requestDelay
                        } else 0
                    )
                )
            }

            else -> {
                success(Pair(apiResponse.raw, response))
            }
        }
    }

    override suspend fun onError(apiResponse: ApiResponse.Failure.Error<Result>) {
        when (apiResponse.statusCode) {
            StatusCode.Unauthorized,
            StatusCode.Forbidden -> {
                noAuth()
            }

            StatusCode.ServiceUnavailable -> {
                maintenance(apiResponse.response.message())
            }

            StatusCode.NotFound -> {
                fail(
                    SimpleNetworkModel(
                        StatusCode.NotFound.name,
                        apiResponse.response.message(),
                        Constants.TEXT_BLANK
                    )
                )
            }

            else -> {
                fail(
                    SimpleNetworkModel(
                        Api.RequestStatus.FAIL.status,
                        apiResponse.response.message(),
                        Constants.TEXT_BLANK
                    )
                )
            }
        }
    }

    override suspend fun onException(apiResponse: ApiResponse.Failure.Exception<Result>) {
        fail(
            SimpleNetworkModel(
                Api.RequestStatus.FAIL.status,
                apiResponse.message,
                Constants.TEXT_BLANK
            )
        )
    }
}