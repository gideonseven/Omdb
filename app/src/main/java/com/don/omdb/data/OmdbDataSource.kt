package com.don.omdb.data

import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import com.don.omdb.api.MovieService
import com.don.omdb.data.remote.MdlMovieList
import com.don.omdb.data.remote.RemoteRepository


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

    fun getError():LiveData<String>

}