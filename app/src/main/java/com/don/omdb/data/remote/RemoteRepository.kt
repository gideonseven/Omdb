package com.don.omdb.data.remote

import com.don.omdb.api.MovieService
import com.don.omdb.utils.JniHelper
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * Created by gideon on 05,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
class RemoteRepository {
    fun getMovies(
        loadMoviesCallback: LoadMoviesCallback,
        movieService: MovieService,
        page: Int,
        keyword: String,
        onComplete: (() -> Unit)? = null
    ) {
        movieService.getMoviesList(JniHelper.apiKey(), keyword, "movie", page)
            .enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val jsonBody = response.body()!!.asJsonObject.toString()
                            Timber.d(jsonBody)

                            val jsonParser = JsonParser()
                            val jo = jsonParser.parse(jsonBody) as JsonObject

                            if (jo.has("Search")) {
                                val results = jo.getAsJsonArray("Search").toString()
                                val listType = object : TypeToken<List<MdlMovieList>>() {}.type
                                val listMovies = Gson().fromJson<List<MdlMovieList>>(results, listType)
                                loadMoviesCallback.onAllMoviesReceived(listMovies)
                            } else {
                                Timber.e(jo.get("Error").asString)
                                loadMoviesCallback.onDataNotAvailable(jo.get("Error").asString)
                            }
                        }
                        onComplete?.invoke()
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Timber.e(t, "Failed to get movies!")
                    loadMoviesCallback.onDataNotAvailable(t.message.toString())
                    onComplete?.invoke()
                }
            })
    }

    //compose version
    fun getDetails(
        callback: LoadDetailCallback,
        movieService: MovieService,
        imdbID: String?,
        onLoadingChange: ((Boolean) -> Unit)? = null
    ) {
        onLoadingChange?.invoke(true)

        movieService.getDetailMovie(JniHelper.apiKey(), imdbID)
            .enqueue(object : Callback<JsonObject> {
                override fun onResponse(
                    call: Call<JsonObject?>,
                    response: Response<JsonObject?>
                ) {
                    onLoadingChange?.invoke(false)

                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val jsonBody = response.body()!!.asJsonObject.toString()
                            Timber.d(jsonBody)

                            val jsonParser = JsonParser()
                            val jo = jsonParser.parse(jsonBody) as JsonObject

                            val detail = Gson().fromJson(jo, MdlDetail::class.java)
                            callback.onDetailReceived(detail)
                        }
                    }

                }

                override fun onFailure(
                    call: Call<JsonObject?>,
                    t: Throwable
                ) {
                    Timber.e(t, "Failed to get Movie Details!")
                    onLoadingChange?.invoke(false)
                    callback.onDataNotAvailable(t.message.toString())
                }
            })
    }


    interface LoadMoviesCallback {
        fun onAllMoviesReceived(courseResponses: List<MdlMovieList>)
        fun onDataNotAvailable(response: String)
    }

    interface LoadDetailCallback {
        fun onDetailReceived(mdlDetail: MdlDetail)
        fun onDataNotAvailable(response: String)
    }
}