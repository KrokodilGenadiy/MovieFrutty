package com.example.moviefrutty.home.domain.interactors

import com.example.moviefrutty.core.util.Resource
import com.example.moviefrutty.core.util.error_handling.DataError
import com.example.moviefrutty.home.data.entity.Movie
import kotlinx.coroutines.flow.Flow

interface HomeScreenInteractor {
    suspend fun getMoviesFromApi(
        query: String,
        page: Int
    ): Flow<Resource<List<Movie>, DataError.Network>>
    fun saveDefaultCategoryToPreferences(category: String)
    fun getDefaultCategoryFromPreferences(): String
}