package com.don.omdb.ui.main

import com.don.omdb.data.remote.UnsplashItem
import com.don.omdb.utils.*


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
class MainContract {

    sealed class MainEffect : UiEffect {
        object DoSomethingHappened: MainEffect()
    }

    sealed class MainEvent : UiEvent {
        object DoSomethingForMe: MainEvent()
    }

    data class MainState(
        val responseState: ResponseState<RequestType, List<UnsplashItem>?> = ResponseState.Empty
    ) : UiState
}