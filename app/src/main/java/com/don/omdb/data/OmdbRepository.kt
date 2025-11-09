package com.don.omdb.data

import com.don.omdb.api.MovieService
import com.don.omdb.data.remote.RemoteRepository


/**
 * Created by gideon on 05,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
class OmdbRepository(private val remoteRepository: RemoteRepository) {

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

    // ========== New Compose-Friendly Methods ==========

    /**
     * Uses callbacks for direct state management in ViewModels
     */
    fun getMovies(
        callback: RemoteRepository.LoadMoviesCallback,
        movieService: MovieService,
        page: Int,
        keyword: String
    ) {
        remoteRepository.getMovies(callback, movieService, page, keyword)
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
}