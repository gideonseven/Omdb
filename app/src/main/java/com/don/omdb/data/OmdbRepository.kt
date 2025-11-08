package com.don.omdb.data

import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.don.omdb.api.MovieService
import com.don.omdb.data.remote.MdlDetail
import com.don.omdb.data.remote.MdlMovieList
import com.don.omdb.data.remote.RemoteRepository


/**
 * Created by gideon on 05,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
class OmdbRepository(private val remoteRepository: RemoteRepository) : OmdbDataSource {


    val errorResponse = MutableLiveData<String>()

    companion object {
        @Volatile
        private var INSTANCE: OmdbRepository? = null

        fun getInstance(remoteData: RemoteRepository): OmdbRepository? {
            if (INSTANCE == null) {
                synchronized(OmdbRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = OmdbRepository(remoteData)
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getMovies(
        movieService: MovieService,
        page: Int,
        keyword: String,
        progress: LinearLayout
    ): LiveData<List<MdlMovieList>> {
        val movieResults = MutableLiveData<List<MdlMovieList>>()
        remoteRepository.getMovies(
            object : RemoteRepository.LoadMoviesCallback {
                override fun onAllMoviesReceived(courseResponses: List<MdlMovieList>) {
                    movieResults.postValue(courseResponses)
                }

                override fun onDataNotAvailable(response: String) {
                    errorResponse.postValue(response)
                }
            },
            movieService,
            page,
            keyword,
            progress
        )


        return movieResults
    }


    // old method
    override fun getDetails(
        movieService: MovieService,
        imdbID: String?,
        progress: LinearLayout
    ): LiveData<MdlDetail> {
        val movieDetail = MutableLiveData<MdlDetail>()

        remoteRepository.getDetails(object : RemoteRepository.LoadDetailCallback {
            override fun onDetailReceived(mdlDetail: MdlDetail) {
                movieDetail.postValue(mdlDetail)
            }

            override fun onDataNotAvailable(response: String) {
                errorResponse.postValue(response)
            }

        }, movieService, imdbID, progress)

        return movieDetail
    }

    //compose version
    fun getMovieDetail(
        callback: RemoteRepository.LoadDetailCallback,
        movieService: MovieService,
        imdbID: String?,
        onLoadingChange: ((Boolean) -> Unit)? = null
    ) {
        remoteRepository.getDetails(callback, movieService, imdbID, onLoadingChange)
    }


    override fun getError(): LiveData<String> {
        return errorResponse
    }


}