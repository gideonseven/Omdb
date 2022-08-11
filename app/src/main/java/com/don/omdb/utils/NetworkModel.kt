package com.don.omdb.utils

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
interface BaseNetworkModel {
    val status: String?
    val message: String?
}

//for unsplash
/*@Parcelize
data class SimpleNetworkModel(
    @SerializedName("status") override val status: String? = null,
    @SerializedName("message") override val message: String? = null
) : BaseNetworkModel, Parcelable*/

//for movie
@Parcelize
@JsonClass(generateAdapter = true)
data class SimpleNetworkModel(
    @Json(name = "status")
    override val status: String? = null,
    @Json(name = "status_message")
    override val message: String? = null
) : BaseNetworkModel, Parcelable