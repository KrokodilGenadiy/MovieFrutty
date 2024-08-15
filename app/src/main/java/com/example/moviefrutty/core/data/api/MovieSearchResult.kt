package com.example.moviefrutty.core.data.api

import com.example.moviefrutty.home.data.entity.Movie
import com.google.gson.annotations.SerializedName

data class MovieSearchResult(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val tmdbFilms: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)