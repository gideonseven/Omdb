package com.don.omdb.ui.main

import com.don.omdb.data.remote.MdlMovieList
import com.don.omdb.utils.*


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
class MainContract {

    sealed class MainEffect : UiEffect {
        object onDoSomethingHappened: MainEffect()
    }

    sealed class MainEvent : UiEvent {
        object doSomethingForMe: MainEvent()
    }

    data class MainState(
        val responseState: ResponseState<RequestType, MdlMovieList?> = ResponseState.Empty
    ) : UiState
}