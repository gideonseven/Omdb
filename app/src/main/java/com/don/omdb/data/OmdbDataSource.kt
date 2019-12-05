package com.don.omdb.data

import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import com.don.omdb.api.MovieService
import com.don.omdb.data.remote.MdlDetail
import com.don.omdb.data.remote.MdlMovieList


/**
 * Created by gideon on 05,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
interface OmdbDataSource {

    fun getMovies(movieService: MovieService,
                  page: Int,
                  keyword: String,
                  progress: LinearLayout): LiveData<List<MdlMovieList>>

    fun getDetails(movieService: MovieService, imdbID: String?, progress: LinearLayout): LiveData<MdlDetail>

    fun getError(): LiveData<String>

}