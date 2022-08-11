package com.don.omdb.data.remote.movies

import com.squareup.moshi.Json

data class Movies(

	@Json(name="dates")
	val dates: Dates? = null,

	@Json(name="page")
	val page: Int? = null,

	@Json(name="total_pages")
	val totalPages: Int? = null,

	@Json(name="results")
	val results: List<ResultsItem>? = null,

	@Json(name="total_results")
	val totalResults: Int? = null
)