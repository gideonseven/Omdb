package com.don.omdb.ui.detail

import android.annotation.SuppressLint
import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.don.omdb.api.MovieService
import com.don.omdb.data.OmdbRepository
import com.don.omdb.data.remote.MdlDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
//@SuppressLint("StaticFieldLeak")
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val omdbRepository: OmdbRepository,
    private val mMovieService: MovieService
) : ViewModel() {


    @SuppressLint("StaticFieldLeak")
    private lateinit var mProgress: LinearLayout
    private var imdbID: String? = null

    fun getErrors(): LiveData<String> {
        return omdbRepository.getError()
    }

    fun getDetail(): LiveData<MdlDetail> {
        return omdbRepository.getDetails(mMovieService, imdbID, mProgress)
    }

    fun setAttributes(imdbID: String?, view: LinearLayout) {
        this.imdbID = imdbID
        this.mProgress = view
    }
}
