package com.demo.codingassignmentlloyds.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.codingassignmentlloyds.domain.datamodel.MovieDetail
import com.demo.codingassignmentlloyds.domain.usecase.MovieLDetailUseCase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val useCase: MovieLDetailUseCase,
) : ViewModel() {

    private val _stateFlow =
        MutableStateFlow<ViewState<MovieDetail>>(ViewState.Loading(true))

    fun fetchMovieDetail() {
        viewModelScope.launch {
            com.llyods.assignment.getViewStateFlow {
                useCase()
            }.collect {
                when (it) {
                    is ViewState.Loading -> _stateFlow.value = it
                    is ViewState.Failure -> _stateFlow.value = it
                    is ViewState.Success -> {
                        it.output.let { artists ->
                            _stateFlow.value = ViewState.Success(artists)
                        }
                    }
                }
            }
        }
    }

    fun getViewStateFlow(): StateFlow<ViewState<MovieDetail>> = _stateFlow

}