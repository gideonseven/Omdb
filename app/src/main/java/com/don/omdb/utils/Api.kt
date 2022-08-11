package com.don.omdb.utils


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
object Api {
    const val DEVICE = "device"
    const val VERSION = "version"
    const val AUTH_HEADER = "Authorization"
    const val CLIENT_ID = "Client-ID"

    enum class RequestStatus(val status: String) {
        SUCCESS("success"),
        FAIL("fail"),
        BAD_REQUEST("400"),
        UNAUTHORIZED("401"),
        FORBIDDEN("403"),
        NOT_FOUND("404"),
        SERVER_ERROR("500"),
    }
}