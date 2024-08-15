package com.example.moviefrutty.home.data.implementations

import android.content.Context
import com.example.moviefrutty.core.data.api.API
import com.example.moviefrutty.core.data.api.MovieApi
import com.example.moviefrutty.core.util.PreferenceProvider
import com.example.moviefrutty.core.util.Resource
import com.example.moviefrutty.core.util.error_handling.DataError
import com.example.moviefrutty.core.util.error_handling.NetworkHelper.isOnline
import com.example.moviefrutty.home.data.entity.Movie
import com.example.moviefrutty.home.domain.repositories.HomeScreenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException
import java.util.Locale

class HomeScreenRepositoryImpl(
    private val retrofitService: MovieApi,
    private val preferences: PreferenceProvider,
    private val context: Context
) : HomeScreenRepository {
    override suspend fun getFilmsFromApi(page: Int): Flow<Resource<List<Movie>, DataError.Network>> =
        flow<Resource<List<Movie>, DataError.Network>> {

            if (!isOnline(context)) {
                emit(Resource.Error(DataError.Network.NO_INTERNET))
            } else {
                var movies = emptyList<Movie>()
                try {
                    movies = retrofitService.getFilms(
                        getDefaultCategoryFromPreferences(),
                        API.KEY, Locale.getDefault().toLanguageTag(), page
                    ).tmdbFilms
                } catch (e: Exception) {
                    when (e) {
                        is HttpException -> {
                            when (e.code()) {
                                404 -> emit(Resource.Error(DataError.Network.NOT_FOUND))
                                408 -> emit(Resource.Error(DataError.Network.REQUEST_TIMEOUT))
                                413 -> emit(Resource.Error(DataError.Network.PAYLOAD_TOO_LARGE))
                                else -> emit(Resource.Error(DataError.Network.UNKNOWN))
                            }
                        }
                        is IOException -> {
                            emit(Resource.Error(DataError.Network.UNKNOWN))
                        }
                    }
                }
                emit(Resource.Success(movies))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getFilmsByQuery(
        query: String,
        page: Int
    ): Flow<Resource<List<Movie>, DataError.Network>> =
        flow<Resource<List<Movie>, DataError.Network>> {
            if (!isOnline(context)) {
                emit(Resource.Error(DataError.Network.NO_INTERNET))
            }
            var movies = emptyList<Movie>()
            try {
                movies = retrofitService.getFilmFromSearch(
                    API.KEY,
                    Locale.getDefault().toLanguageTag(),
                    query,
                    page
                ).tmdbFilms
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        when (e.code()) {
                            404 -> emit(Resource.Error(DataError.Network.NOT_FOUND))
                            408 -> emit(Resource.Error(DataError.Network.REQUEST_TIMEOUT))
                            413 -> emit(Resource.Error(DataError.Network.PAYLOAD_TOO_LARGE))
                            else -> emit(Resource.Error(DataError.Network.UNKNOWN))
                        }
                    }
                    is IOException -> {
                        emit(Resource.Error(DataError.Network.UNKNOWN))
                    }
                }
            }
            emit(Resource.Success(movies))
        }.flowOn(Dispatchers.IO)

    override fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }

    override fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()
}