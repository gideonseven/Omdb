package com.don.omdb.data.remote

import android.view.View
import android.widget.LinearLayout
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
        progress: LinearLayout
    ) {
        //hardcoded t = dragon
        progress.visibility = View.VISIBLE
        movieService.getMoviesList(JniHelper.apiKey(), keyword, "movie", page)
            .enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            //Get response from JsonObject save itu into string
                            val jsonBody = response.body()!!.asJsonObject.toString()
                            Timber.d(jsonBody)

                            //Parse JsonObject and get the array list with Key "results"
                            val jsonParser = JsonParser()
                            val jo = jsonParser.parse(jsonBody) as JsonObject

                            //add the  result to 'list'
                            if (jo.has("Search")) {
                                val results = jo.getAsJsonArray("Search").toString()
                                val listType = object : TypeToken<List<MdlMovieList>>() {}.type
                                val listMoviez =
                                    Gson().fromJson<List<MdlMovieList>>(results, listType)
//                                listMovie.postValue(listMoviez)

                                loadMoviesCallback.onAllMoviesReceived(listMoviez)
                            } else {
                                //stringError.postValue(jo.get("Error").asString)
//                                    errorResponse.postValue(jo.get("Error").asString)
                                Timber.e(jo.get("Error").asString)
                                loadMoviesCallback.onDataNotAvailable(jo.get("Error").asString)

                            }
                        }
                        progress.visibility = View.GONE
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Timber.e(t, "Failed to get movies!")
                    progress.visibility = View.GONE
//                        errorResponse.postValue(t.message.toString())
                    loadMoviesCallback.onDataNotAvailable(t.message.toString())
                }
            })
    }

    fun getDetails(
        callback: LoadDetailCallback,
        movieService: MovieService,
        imdbID: String?,
        progress: LinearLayout
    ) {
        movieService.getDetailMovie(JniHelper.apiKey(), imdbID)
            .enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            //Get response from JsonObject save itu into string
                            val jsonBody = response.body()!!.asJsonObject.toString()
                            Timber.d(jsonBody)
                            //Parse JsonObject
                            val jsonParser = JsonParser()
                            val jo = jsonParser.parse(jsonBody) as JsonObject

                            val detail = Gson().fromJson(jo, MdlDetail::class.java)
                            callback.onDetailReceived(detail)
                        }
                        progress.visibility = View.GONE
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Timber.e(t, "Failed to get movies!")
//                        errorResponse.postValue()
                    progress.visibility = View.GONE
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