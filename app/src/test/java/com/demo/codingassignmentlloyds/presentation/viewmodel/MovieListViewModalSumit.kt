package com.demo.codingassignmentlloyds.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.codingassignmentlloyds.MainCoroutineRule
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.datamodel.Result
import com.demo.codingassignmentlloyds.domain.usecase.MovieListUseCase
import com.demo.codingassignmentlloyds.presentation.ViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.inOrder
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class MovieListViewModalSumit {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()
    @Mock
    private lateinit var useCase: MovieListUseCase


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    private val testDispatcher = TestCoroutineDispatcher()


//    @Test
//    fun fetchMovieListTest() = runBlocking {
//        val viewModel = MovieListViewModel(useCase, testDispatcher)
//        Mockito.`when`(useCase.fetchData()).thenReturn(fakeSuccessFlow)
//
//        Mockito.`when`(
//            viewModel.getViewStateFlowFromResponse(useCase.fetchData())
//        ).thenReturn(fakeSuccessViewState)
//
//        viewModel.fetchMovieList()
//        val viewStateResponse = viewModel.getViewStateFlow()
//
////        val stage1 = ViewState.Loading(true)
////        val stage2 = ViewState.Success(emptyList<Movie>())
////        val stage3 = ViewState.Loading(false)
////        val inOrder = inOrder(stage1, stage2,stage3)
////
////        inOrder.verify(stage1).isLoading
//
//        Assert.assertEquals(ViewState.Success(emptyList<Movie>()), viewStateResponse.value)
//
//    }
}
