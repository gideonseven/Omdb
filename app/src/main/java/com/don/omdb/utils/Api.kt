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
        UNAUTHORIZED("403"),
        NOT_FOUND("404"),
        MAINTENANCE("maintenance"),
        BLACKLIST("blacklist"),
        OBSOLETE("obsolete_app");
    }
}