package com.demo.codingassignmentlloyds.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.demo.codingassignmentlloyds.presentation.ViewState
import com.demo.codingassignmentlloyds.presentation.ViewState.Loading
import com.demo.codingassignmentlloyds.presentation.ViewState.Success
import com.demo.codingassignmentlloyds.presentation.ViewState.Failure
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.usecase.MovieListUseCase
import com.demo.codingassignmentlloyds.injection.CoroutineScopeDefault
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val useCase: MovieListUseCase,
    @CoroutineScopeDefault
    dispatchers: CoroutineDispatcher
) : BaseViewModel(dispatchers) {

    private val _uiStateFlow =
        MutableStateFlow<ViewState<List<Movie>>>(Loading(true))

    fun fetchMovieList() {
        viewModelScope.launch {
            val movieListWebServiceResponse = useCase()
            getViewStateFlowFromResponse(
                movieListWebServiceResponse
            ).collect { viewState ->
                _uiStateFlow.value = when (viewState) {
                    is Loading -> viewState
                    is Failure -> Failure(viewState.throwable)
                    is Success -> Success(viewState.result)
                }
            }
        }
    }

    fun getViewStateFlow(): StateFlow<ViewState<List<Movie>>> = _uiStateFlow
}