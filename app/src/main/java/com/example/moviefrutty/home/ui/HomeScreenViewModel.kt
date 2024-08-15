package com.example.moviefrutty.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviefrutty.core.util.Resource
import com.example.moviefrutty.core.util.asUiText
import com.example.moviefrutty.home.data.entity.Movie
import com.example.moviefrutty.home.domain.events.MoviesEvent
import com.example.moviefrutty.home.domain.repositories.HomeScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val interactor: HomeScreenRepository) :
    ViewModel() {

    private val eventChannel = Channel<MoviesEvent.Error>()
    val events = eventChannel.receiveAsFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    fun onSearchTextChange(query: String) {
        _searchQuery.value = query
    }

    private val _movies = MutableStateFlow(emptyList<Movie>())

    val movies = searchQuery
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_movies) { query, movies ->
            if (query.isBlank()) {
                searchMovies(1,"")
                movies
            } else {
                searchMovies(1,query)
                movies
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _movies.value
        )


    private fun searchMovies( page: Int, query: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                interactor.getMoviesFromApi(page,query).collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _movies.value = result.data
                        }
                        is Resource.Error -> {
                            val errorMessage = result.error.asUiText()
                            eventChannel.send(MoviesEvent.Error(errorMessage))
                        }
                    }
                }
            }
        }
    }
}