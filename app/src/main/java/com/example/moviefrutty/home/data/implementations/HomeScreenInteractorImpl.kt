package com.example.moviefrutty.home.data.implementations

import com.example.moviefrutty.core.util.Resource
import com.example.moviefrutty.core.util.error_handling.DataError
import com.example.moviefrutty.home.data.entity.Movie
import com.example.moviefrutty.home.domain.interactors.HomeScreenInteractor
import com.example.moviefrutty.home.domain.repositories.HomeScreenRepository
import kotlinx.coroutines.flow.Flow

class HomeScreenInteractorImpl(private val repository: HomeScreenRepository) :
    HomeScreenInteractor {

    override suspend fun getMoviesFromApi(
        query: String,
        page: Int
    ): Flow<Resource<List<Movie>, DataError.Network>> = repository.getMoviesFromApi(page, query)

    override fun saveDefaultCategoryToPreferences(category: String) {
        repository.saveDefaultCategoryToPreferences(category)
    }

    override fun getDefaultCategoryFromPreferences(): String =
        repository.getDefaultCategoryFromPreferences()
}