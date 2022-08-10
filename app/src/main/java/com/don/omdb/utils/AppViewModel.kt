package com.don.omdb.utils


/**
 * Created by gideon on 8/10/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
abstract class AppViewModel<Event : UiEvent, State : UiState, Effect : UiEffect> :
    CoreViewModel<Event, State, Effect>()