package com.netguru.testmovies.common

sealed class ViewState {
    object LOADING: ViewState()
    object EMPTY: ViewState()
    object DONE: ViewState()
    data class ERROR(val message: String?): ViewState()
}
