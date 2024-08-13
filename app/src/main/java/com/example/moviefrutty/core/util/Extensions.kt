package com.example.moviefrutty.core.util

import com.example.moviefrutty.R
import com.example.moviefrutty.core.util.error_handling.DataError

fun List<Int>.convertToGenres() =
    this.map {
        Genres.genreMap[it]
    }

fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(
            R.string.the_request_timed_out
        )

        DataError.Network.NO_INTERNET -> UiText.StringResource(
            R.string.no_internet
        )

        DataError.Network.SERVER_ERROR -> UiText.StringResource(
            R.string.server_error
        )

        DataError.Network.UNKNOWN -> UiText.StringResource(
            R.string.unknown_error
        )

        DataError.Local.DISK_FULL -> UiText.StringResource(
            R.string.error_disk_full
        )

        DataError.Network.PAYLOAD_TOO_LARGE -> UiText.StringResource(
            R.string.payload_too_large
        )
        DataError.Network.NOT_FOUND -> UiText.StringResource(
            R.string.not_found
        )
    }
}

fun Resource.Error<*, DataError>.asErrorUiText(): UiText {
    return error.asUiText()
}
