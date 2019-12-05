package com.don.omdb.ui.main

import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.don.omdb.api.MovieService
import com.don.omdb.data.OmdbRepository
import com.don.omdb.data.remote.MdlMovieList
import com.don.omdb.data.remote.RemoteRepository

/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
class MainViewModel : ViewModel() {
    lateinit var mMovieService: MovieService
    lateinit var mProgress: LinearLayout
    var mPage: Int = 0
    var mKeyword = ""


    val omdbRepository: OmdbRepository = OmdbRepository.getInstance(RemoteRepository())!!


    fun getErrors(): LiveData<String> {
        return omdbRepository.getError()
    }

    fun getMovies(): LiveData<List<MdlMovieList>> {
        return omdbRepository.getMovies(mMovieService, mPage, mKeyword, mProgress)
    }

    fun setAttributes(movieService: MovieService, page: Int, keyword: String, view: LinearLayout) {
        this.mMovieService = movieService
        this.mPage = page
        this.mKeyword = keyword
        this.mProgress = view
    }


}