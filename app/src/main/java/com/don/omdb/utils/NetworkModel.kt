package com.don.omdb.utils

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
interface BaseNetworkModel {
    val status: String?
    val message: String?
    val token: String?
}

@Parcelize
data class SimpleNetworkModel(
    @SerializedName("status") override val status: String? = null,
    @SerializedName("message") override val message: String? = null,
    @SerializedName("token") override val token: String? = null,
    @SerializedName("request_delay") val requestDelay: Int = 0
) : BaseNetworkModel, Parcelable