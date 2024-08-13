package com.example.moviefrutty.home.domain.events

import com.example.moviefrutty.core.util.UiText

sealed interface MoviesEvent {
    data class Error(val error: UiText): MoviesEvent
}