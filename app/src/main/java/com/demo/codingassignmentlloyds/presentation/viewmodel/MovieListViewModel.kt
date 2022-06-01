package com.demo.codingassignmentlloyds.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.usecase.MovieListUseCase
import com.llyods.assignment.getViewStateFlow
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val useCase: MovieListUseCase,
) : ViewModel() {

    private val _stateFlow =
        MutableStateFlow<ViewState<List<Movie>>>(ViewState.Loading(true))


    fun fetchMovieList() {
        viewModelScope.launch {
            getViewStateFlow {
                useCase()
            }.collect {
                when (it) {
                    is ViewState.Loading -> _stateFlow.value = it
                    is ViewState.Failure -> _stateFlow.value = it
                    is ViewState.Success -> {
                        it.output.let { movies ->
                            _stateFlow.value = ViewState.Success(movies)
                        }
                    }
                }
            }
        }
    }

    fun getViewStateFlow(): StateFlow<ViewState<List<Movie>>> = _stateFlow

}