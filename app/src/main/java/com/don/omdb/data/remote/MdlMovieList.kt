package com.don.omdb.data.remote

import com.don.omdb.utils.BaseNetworkModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MdlMovieList(
    @field:SerializedName("loading") @field:Expose var loading: String = "",
    val Type: String? = null,
    val Year: String? = null,
    val imdbID: String? = null,
    val Poster: String? = null,
    val Title: String? = null,

    @field:SerializedName("loading")
    override val status: String? = null,
    @field:SerializedName("loading")
    override val message: String? = null,
    @field:SerializedName("loading")
    override val token: String? = null
) : BaseNetworkModel
