package com.demo.codingassignmentlloyds.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.codingassignmentlloyds.MainCoroutineRule
import com.demo.codingassignmentlloyds.data.repository.MovieRepositoryImpl
import com.demo.codingassignmentlloyds.data.webservice.MovieApi
import com.demo.codingassignmentlloyds.dispatcher.CustomCoroutinesDispatchers
import com.demo.codingassignmentlloyds.domain.usecase.MovieListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class MovieListViewModalTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()
    @Mock
    private lateinit var useCase: MovieListUseCase

    @Mock
    private lateinit var dispatcher: CustomCoroutinesDispatchers

    @Mock
    private lateinit var apiHelper: MovieApi

    @Mock
    private lateinit var repository: MovieRepositoryImpl

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    private val testDispatcher = TestCoroutineDispatcher()
    @Test
    fun fetchMovieListTest() = testDispatcher.runBlockingTest {

        val viewModel = MovieListViewModel(useCase,testDispatcher)
    }


}