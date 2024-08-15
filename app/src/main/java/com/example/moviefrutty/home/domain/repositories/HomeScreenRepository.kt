package com.example.moviefrutty.home.domain.repositories

import com.example.moviefrutty.core.util.Resource
import com.example.moviefrutty.core.util.error_handling.DataError
import com.example.moviefrutty.home.data.entity.Movie
import kotlinx.coroutines.flow.Flow

interface HomeScreenRepository {
    suspend fun getMoviesFromApi(
        page: Int,
        query: String
    ): Flow<Resource<List<Movie>, DataError.Network>>
    fun saveDefaultCategoryToPreferences(category: String)
    fun getDefaultCategoryFromPreferences(): String
}