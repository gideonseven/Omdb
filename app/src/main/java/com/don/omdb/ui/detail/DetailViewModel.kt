package com.don.omdb.ui.detail

import androidx.lifecycle.ViewModel
import com.don.omdb.api.MovieService
import com.don.omdb.data.OmdbRepository
import com.don.omdb.data.remote.MdlDetail
import com.don.omdb.data.remote.MovieDetailUi
import com.don.omdb.data.remote.RemoteRepository
import com.don.omdb.data.remote.toMovieDetailUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val movieService: MovieService
) : ViewModel() {

    private val _detailState = MutableStateFlow(DetailUiState())
    val detailState: StateFlow<DetailUiState> = _detailState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()


    fun loadMovieDetail(imdbID: String?) {
        if (imdbID == null) return

        omdbRepository.getMovieDetail(
            object : RemoteRepository.LoadDetailCallback {
                override fun onDetailReceived(mdlDetail: MdlDetail) {
                    _detailState.value = DetailUiState(
                        isLoading = false,
                        movieDetail = mdlDetail.toMovieDetailUi()
                    )
                }

                override fun onDataNotAvailable(response: String) {
                    _detailState.value = _detailState.value.copy(isLoading = false)
                    _errorState.value = response
                }
            },
            movieService,
            imdbID
        ) { isLoading ->
            _detailState.value = _detailState.value.copy(isLoading = isLoading)
        }
    }

}


data class DetailUiState(
    val isLoading: Boolean = false,
    val movieDetail: MovieDetailUi? = null
)