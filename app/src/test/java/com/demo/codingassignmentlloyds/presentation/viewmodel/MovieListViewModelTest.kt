package com.demo.codingassignmentlloyds.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.demo.codingassignmentlloyds.TestCoroutineRule
import com.demo.codingassignmentlloyds.presentation.ViewState
import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.usecase.MovieListUseCase
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieListViewModelTest {

    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    var mainCoroutineRule = TestCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var mockMovieList: List<Movie>

    @RelaxedMockK
    private lateinit var viewStateObserver: Observer<ViewState<List<Movie>>>

    @RelaxedMockK
    private lateinit var mockException: Exception

    @RelaxedMockK
    private lateinit var mockUseCase: MovieListUseCase

    private val fakeSuccessFlow = flow {
        emit(WebServiceResponse.OnSuccess(mockMovieList))
    }

    private val fakeFailureFlow = flow {
        emit(WebServiceResponse.OnFailure(mockException))
    }

    private lateinit var viewModel: MovieListViewModel


    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        every { mockException.message } returns "Exception!!"
        viewModel = MovieListViewModel(mockUseCase)
    }

    @Test
    fun fetchMovieListError(){
        runBlockingTest {
            val movieList: ArrayList<Movie> = mockk()
            coEvery { mockUseCase() } returns fakeSuccessFlow
            viewModel.fetchMovieList()

            verifyOrder {
                viewStateObserver.onChanged(ViewState.Loading(true))
                viewStateObserver.onChanged(ViewState.Success(movieList))
                viewStateObserver.onChanged(ViewState.Loading(false))
            }
        }

    }

    @Test
    fun fetchMovieList(){

        runBlockingTest {
            coEvery { mockUseCase() } returns fakeFailureFlow

            viewModel.fetchMovieList()

            verifyOrder {
                viewStateObserver.onChanged(ViewState.Loading(true))
                viewStateObserver.onChanged(ViewState.Failure(mockException))
                viewStateObserver.onChanged(ViewState.Loading(false))
            }
        }
    }
}