package com.don.omdb.ui.main

import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.don.omdb.api.MovieService
import com.don.omdb.data.OmdbRepository
import com.don.omdb.data.remote.MdlMovieList
import com.don.omdb.data.remote.RemoteRepository
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
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
class MainViewModel : ViewModel() {


    val omdbRepository: OmdbRepository = OmdbRepository.getInstance(RemoteRepository())!!


    fun getErrors(): LiveData<String> {
        return omdbRepository.getError()
    }

    fun getMovies(movieService: MovieService, page: Int, keyword: String, progress: LinearLayout): LiveData<List<MdlMovieList>> {
        return omdbRepository.getMovies(movieService, page, keyword, progress)
    }

}