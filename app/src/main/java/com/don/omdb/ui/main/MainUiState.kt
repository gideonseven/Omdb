package com.don.omdb.ui.main

import com.don.omdb.data.remote.MdlMovieList

data class MainUiState(
    val movies: List<MdlMovieList> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val currentPage: Int = 1,
    val currentQuery: String = "dragon",
    val hasMorePages: Boolean = true,
    val totalPages: Int = 4
)