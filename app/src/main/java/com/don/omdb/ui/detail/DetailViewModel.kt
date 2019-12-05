package com.don.omdb.ui.detail

import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.don.omdb.api.MovieService
import com.don.omdb.data.OmdbRepository
import com.don.omdb.data.remote.MdlDetail
import com.don.omdb.data.remote.RemoteRepository

/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
class DetailViewModel : ViewModel() {

    val omdbRepository: OmdbRepository = OmdbRepository.getInstance(RemoteRepository())!!

    lateinit var mMovieService: MovieService
    lateinit var mProgress: LinearLayout
    var imdbID: String? = null


    fun getErrors(): LiveData<String> {
        return omdbRepository.getError()
    }

    fun getDetail(): LiveData<MdlDetail> {
        return omdbRepository.getDetails(mMovieService, imdbID, mProgress)
    }

    fun setAttributes(movieService: MovieService, imdbID: String?, view: LinearLayout) {
        this.mMovieService = movieService
        this.imdbID = imdbID
        this.mProgress = view
    }
}
