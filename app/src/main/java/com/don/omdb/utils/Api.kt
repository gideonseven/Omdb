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
    const val API_URL = "https://api.cicil.co.id/"
    const val DOMAIN_URL = "https://www.cicil.co.id/"
    const val HOST_SCHEME = "https"
    const val HOST_PATH = "v1"
    const val REQUEST_TIMEOUT = 180L
    const val DEFAULT_RESPONSE_CACHE = 10 * 1024 * 1024 // 10 MiB
    const val BEARER = "Bearer"

    var CONNECTION: Boolean = false

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