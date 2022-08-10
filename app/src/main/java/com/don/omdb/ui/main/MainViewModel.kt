package com.don.omdb.ui.main

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
    var totalPage: Int = 4
    var currentPage = 1
    var myQuery = "dragon"

    init {
        MainContract.MainEvent.doSomethingForMe
    }

    override fun createInitialState(): MainContract.MainState = MainContract.MainState()


    override fun handleEvent(event: MainContract.MainEvent) {
        when(event){
            is MainContract.MainEvent.doSomethingForMe -> doSomething()
        }
        /* when (event) {
             is StatisticEvent.GetStatistic -> getStatistic()
             is StatisticEvent.ClickSeeMore -> setEffect { StatisticEffect.SeeMore(event.url) }
         }*/
    }

//    private val omdbRepository: OmdbRepository = OmdbRepository.getInstance(RemoteRepository())!!

    fun getMovies() {
        launchRequest {
            setState {
                copy(responseState = ResponseState.Loading(RequestType.GET_MOVIES))
            }
            omdbUseCase.getMovies(
                RequestType.GET_MOVIES,
                keyword = myQuery,
                type = "movies",
                page = currentPage
            )
                .handleErrors(RequestType.GET_MOVIES)
                .handleResult(
                    updateState = { responseState ->
                        Timber.e("==== ${responseState}")
                    },
                    onSuccess = { _, result ->
                        Timber.e("==== ${result}")
                    },
                    onFailed = { _, model ->
                        Timber.e("==== ${model.message}")
                    },
                    onNotAuthorized = {
                        Timber.e("==== ${it}")

                    }
                )
        }
    }

    fun doSomething() {
        Timber.e("== do something")
        getMovies()
    }
}