package com.demo.codingassignmentlloyds.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.demo.codingassignmentlloyds.dispatcher.CoroutinesDispatchers
import com.demo.codingassignmentlloyds.dispatcher.CustomCoroutinesDispatchers
import com.demo.codingassignmentlloyds.presentation.ViewState
import com.demo.codingassignmentlloyds.presentation.ViewState.Loading
import com.demo.codingassignmentlloyds.presentation.ViewState.Success
import com.demo.codingassignmentlloyds.presentation.ViewState.Failure
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.usecase.MovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val useCase: MovieListUseCase,
    dispatchers: CoroutinesDispatchers
) : BaseViewModel(dispatchers) {

    private val uiStateFlow =
        MutableStateFlow<ViewState<List<Movie>>>(Loading(true))

    fun fetchMovieList() {
        viewModelScope.launch {
            val movieListWebServiceResponse = useCase.fetchData()
            getViewStateFlowFromWebServiceResponse(
                movieListWebServiceResponse
            ).collect { viewState ->
                uiStateFlow.value = when (viewState) {
                    is Loading,
                    is Failure -> viewState
                    is Success -> Success(viewState.result)
                }
            }
        }
    }

    fun getViewStateFlow(): StateFlow<ViewState<List<Movie>>> = uiStateFlow
}