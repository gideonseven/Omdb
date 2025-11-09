package com.don.omdb.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.don.omdb.api.MovieService
import com.don.omdb.data.OmdbRepository
import com.don.omdb.data.remote.MdlMovieList
import com.don.omdb.data.remote.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val omdbRepository: OmdbRepository,
    private val movieService: MovieService
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        // Load initial movies
        loadMovies()
    }

    fun searchMovies(query: String) {
        if (query.isBlank()) return

        _uiState.update {
            it.copy(
                movies = emptyList(),
                currentPage = 1,
                currentQuery = query,
                hasMorePages = true
            )
        }
        loadMovies()
    }

    fun loadNextPage() {
        val currentState = _uiState.value
        if (currentState.isLoading || !currentState.hasMorePages) return

        _uiState.update { it.copy(currentPage = it.currentPage + 1) }
        loadMovies()
    }

    private fun loadMovies() {
        val currentState = _uiState.value

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            omdbRepository.getMovies(
                object : RemoteRepository.LoadMoviesCallback {
                    override fun onAllMoviesReceived(movieResponses: List<MdlMovieList>) {
                        _uiState.update { state ->
                            val updatedMovies = if (state.currentPage == 1) {
                                movieResponses
                            } else {
                                state.movies + movieResponses
                            }

                            state.copy(
                                movies = updatedMovies,
                                isLoading = false,
                                hasMorePages = state.currentPage < state.totalPages
                            )
                        }
                        Timber.d("Loaded ${movieResponses.size} movies, total: ${_uiState.value.movies.size}")
                    }

                    override fun onDataNotAvailable(response: String) {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = response,
                                hasMorePages = false
                            )
                        }
                        Timber.e("Error loading movies: $response")
                    }
                },
                movieService,
                currentState.currentPage,
                currentState.currentQuery
            )
        }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}