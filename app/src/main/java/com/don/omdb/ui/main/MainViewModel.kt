package com.don.omdb.ui.main

import androidx.lifecycle.MutableLiveData
import com.don.omdb.usecase.IOmdbUseCase
import com.don.omdb.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val omdbUseCase: IOmdbUseCase
) : AppViewModel<MainContract.MainEvent, MainContract.MainState, MainContract.MainEffect>() {

    companion object {
        const val TYPE = "latest"
        const val PER_PAGE = 3
    }

    var query = Constants.TEXT_BLANK

    var currentPage = Constants.ONE

    var isLinear = MutableLiveData<Boolean>().apply {
        value = null
    }

    init {
        setEvent(MainContract.MainEvent.GetPhotos)
    }

    override fun createInitialState(): MainContract.MainState = MainContract.MainState()

    override fun handleEvent(event: MainContract.MainEvent) {
        when (event) {
            is MainContract.MainEvent.GetPhotos -> {
                getMovies2()
            }
            is MainContract.MainEvent.GetSearchPhotos -> {
                getSearchMovie()
            }
            is MainContract.MainEvent.UpdateListAdapter -> setEffect {
                MainContract.MainEffect.AddNewList(
                    event.list
                )
            }
        }
    }

    /* private fun getMovies() {
         Timber.e("CALL GET MOVIES")
         launchRequest {
             setState {
                 copy(responseState = ResponseState.Loading(RequestType.GET_MOVIES))
             }
             omdbUseCase.getMovies(
                 RequestType.GET_MOVIES,
                 page = currentPage,
                 perPage = PER_PAGE,
                 orderBy = TYPE
             )
                 .handleErrors(RequestType.GET_MOVIES)
                 .handleResult(
                     updateState = {
                         setState { copy(responseState = it) }
                     },
                     onSuccess = { _, result ->
                         result?.let {
                             Timber.e("== onSuccess VM ${it.size}")
                             Timber.e("==== ${it[0]}")
                             val listImages: ArrayList<UnsplashItem> = result as ArrayList
                             listImages.add(UnsplashItem(id = Constants.LOADING_ID))

                             setEvent(MainContract.MainEvent.UpdateListAdapter(listImages))

                             *//*    listUnsplashItem = listUnsplashItem
                                listUnsplashItem.value?.let { list ->
                                }*//*
                        }
                    },
                    onFailed = { _, model ->
                        Timber.e("== onFailed VM")
                        Timber.e("==== ${model.message}")
                    },
                    onNotAuthorized = {
                        Timber.e("== onNotAuthorized VM")
                        Timber.e("==== ${it}")
                    }
                )
        }
    }*/

    private fun getMovies2() {
        Timber.e("CALL GET MOVIES")
        launchRequest {
            setState {
                copy(responseStateMovies = ResponseState.Loading(RequestType.GET_MOVIES))
            }
            omdbUseCase.getMovies(
                RequestType.GET_MOVIES,
                page = currentPage
            )
                .handleErrors(RequestType.GET_MOVIES)
                .handleResult(
                    updateState = {
                        setState { copy(responseStateMovies = it) }
                    },
                    onSuccess = { _, movies ->
                        movies?.results?.let {
                            setEvent(MainContract.MainEvent.UpdateListAdapter(it))
                        }
                    },
                    onNotAuthorized = {
                        setEffect { MainContract.MainEffect.ShowToastUnauthorized("FORBIDDEN") }
                    }
                )
        }
    }

    private fun getSearchMovie() {
        Timber.e("CALL SEARCH MOVIES")
        Timber.e(" ===== $currentPage")
        Timber.e(" ===== $query")
        launchRequest {
            setState {
                copy(responseStateSearchMovies = ResponseState.Loading(RequestType.GET_SEARCH_MOVIES))
            }
            omdbUseCase.getSearchMovies(
                RequestType.GET_SEARCH_MOVIES,
                page = currentPage,
                query = query
            )
                .handleErrors(RequestType.GET_SEARCH_MOVIES)
                .handleResult(
                    updateState = {
                        setState { copy(responseStateSearchMovies = it) }
                    },
                    onSuccess = { _, movies ->
                        movies?.results?.let {
                            setEvent(MainContract.MainEvent.UpdateListAdapter(it))
                        }
                    },
                    onNotAuthorized = {
                        setEffect { MainContract.MainEffect.ShowToastUnauthorized("FORBIDDEN") }
                    }
                )
        }
    }
}