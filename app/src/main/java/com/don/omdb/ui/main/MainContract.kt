package com.don.omdb.ui.main

import com.don.omdb.data.remote.UnsplashItem
import com.don.omdb.data.remote.movies.Movies
import com.don.omdb.data.remote.movies.ResultsItem
import com.don.omdb.utils.*


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
class MainContract {

    sealed class MainEffect : UiEffect {
//        object DoSomethingHappened: MainEffect()
//        class AddNewList(val list: ArrayList<UnsplashItem>): MainEffect()
        class AddNewList(val list: List<ResultsItem>): MainEffect()
        class ShowToastUnauthorized(val message: String): MainEffect()
    }

    sealed class MainEvent : UiEvent {
        object GetPhotos: MainEvent()
        object GetSearchPhotos: MainEvent()
//        class UpdateListAdapter(val list: ArrayList<UnsplashItem>): MainEvent()
        class UpdateListAdapter(val list: List<ResultsItem>): MainEvent()
    }

    data class MainState(
//        val responseState: ResponseState<RequestType, List<UnsplashItem>?> = ResponseState.Empty,
        val responseStateMovies: ResponseState<RequestType, Movies?> = ResponseState.Empty,
        val responseStateSearchMovies: ResponseState<RequestType, Movies?> = ResponseState.Empty
    ) : UiState
}