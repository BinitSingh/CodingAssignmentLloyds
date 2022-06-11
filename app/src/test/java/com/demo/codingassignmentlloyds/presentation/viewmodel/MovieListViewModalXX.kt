package com.demo.codingassignmentlloyds.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.demo.codingassignmentlloyds.MainCoroutineRule
import com.demo.codingassignmentlloyds.MockFileReader
import com.demo.codingassignmentlloyds.data.datamapper.MovieListMapper
import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.datamodel.Result
import com.demo.codingassignmentlloyds.domain.usecase.MovieListUseCase
import com.demo.codingassignmentlloyds.presentation.ViewState
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
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
import org.mockito.*
import org.mockito.Mockito.inOrder
import org.mockito.junit.MockitoJUnitRunner
import java.util.*


class MovieListViewModalXX {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MovieListViewModel

    @Mock
    private lateinit var useCase: MovieListUseCase

    private lateinit var viewState: ViewState.Loading

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewState = ViewState.Loading(true)
        viewModel = MovieListViewModel(useCase, testDispatcher)
    }

    private fun getMovieRespose(): List<Movie> {
        val movieListMapperTest = MovieListMapper()
        val fileName = "/MovieItemsListResponse.json"
        val json = MockFileReader().getResponseFromJson(fileName)
        val movieItemsListResponse = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            .adapter(MovieItemsListResponse::class.java).fromJson(json)
        movieItemsListResponse?.let {
            return movieListMapperTest.transformFrom(it)
        }
        return emptyList()
    }
    @Test
    fun fetchMovieListSuccessTest() = runBlocking {
        val flow = flow {
            emit(Result.Success(getMovieRespose()))
        }

        Mockito.`when`(useCase.fetchData()).thenReturn(flow)

        viewModel.fetchMovieList()
        flow.collect {
            Assert.assertEquals(100, it.data.size )
            Assert.assertEquals("tt1745960", it.data.get(0).id )
        }
    }

    @Test
    fun fetchMovieLisLoadingTest() = runBlocking {
        val flow = flow {
            emit(
                Result.Success(emptyList<Movie>())
            )
        }
        Mockito.`when`(useCase.fetchData()).thenReturn(flow)
        viewModel.fetchMovieList()
        Assert.assertEquals(
            ViewState.Loading(true), viewModel.getViewStateFlow().value)
    }
}
