package com.don.omdb.data.remote.movies

import com.squareup.moshi.Json

data class Dates(

	@Json(name="maximum")
	val maximum: String? = null,

	@Json(name="minimum")
	val minimum: String? = null
)