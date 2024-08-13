package com.example.moviefrutty.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviefrutty.core.util.Resource
import com.example.moviefrutty.core.util.asUiText
import com.example.moviefrutty.core.util.error_handling.DataError
import com.example.moviefrutty.home.data.Movie
import com.example.moviefrutty.home.domain.events.MoviesEvent
import com.example.moviefrutty.home.domain.interactors.HomeScreenInteractor
import com.example.moviefrutty.home.domain.repositories.HomeScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val interactor: HomeScreenRepository) :
    ViewModel() {
    private val _moviesFlow = MutableStateFlow<List<Movie>>(emptyList())
    val moviesFlow: StateFlow<List<Movie>> = _moviesFlow.asStateFlow()

    private val eventChannel = Channel<MoviesEvent.Error>()
    val events = eventChannel.receiveAsFlow()

    fun loadFilmsFromApi(page: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                interactor.getFilmsFromApi(page).collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _moviesFlow.value = result.data
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