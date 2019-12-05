package com.don.omdb.ui.detail

import android.app.Application
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.don.omdb.api.MovieService
import com.don.omdb.data.remote.MdlDetail
import com.don.omdb.utils.JniHelper
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
class DetailViewModel() : ViewModel() {
    private val detailMovie = MutableLiveData<MdlDetail>()
    private val errorResponse = MutableLiveData<String>()

    val mDetailMovie: LiveData<MdlDetail> get() = detailMovie
    val errors: LiveData<String> get() = errorResponse

    fun setDetail(movieService: MovieService, imdbID: String, progress: LinearLayout) {
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
                            detailMovie.postValue(detail)
                        }
                        progress.visibility = View.GONE
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Timber.e(t, "Failed to get movies!")
                    errorResponse.postValue(t.message.toString())
                    progress.visibility = View.GONE
                }
            })
    }
}
