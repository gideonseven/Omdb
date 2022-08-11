package com.don.omdb.utils


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
sealed class RequestType : CoreRequestType() {
    object GET_MOVIES : RequestType()
    object GET_SEARCH_MOVIES : RequestType()
}