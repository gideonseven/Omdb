package com.don.omdb.ui.main

import android.annotation.SuppressLint
import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.don.omdb.api.MovieService
import com.don.omdb.data.OmdbRepository
import com.don.omdb.data.remote.MdlMovieList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val omdbRepository: OmdbRepository,
    private val mMovieService: MovieService
) : ViewModel() {
    @SuppressLint("StaticFieldLeak")
    private lateinit var mProgress: LinearLayout
    private var mPage: Int = 0
    private var mKeyword = ""


    fun getErrors(): LiveData<String> {
        return omdbRepository.getError()
    }

    fun getMovies(): LiveData<List<MdlMovieList>> {
        return omdbRepository.getMovies(mMovieService, mPage, mKeyword, mProgress)
    }

    fun setAttributes(page: Int, keyword: String, view: LinearLayout) {
        this.mPage = page
        this.mKeyword = keyword
        this.mProgress = view
    }
}