package com.example.moviefrutty.core.util

fun List<Int>.convertToGenres() =
    this.map {
        Genres.genreMap[it]
    }
