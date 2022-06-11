package com.demo.codingassignmentlloyds.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.codingassignmentlloyds.MainCoroutineRule
import com.demo.codingassignmentlloyds.data.repository.MovieRepositoryImpl
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.datamodel.Result
import com.demo.codingassignmentlloyds.domain.usecase.MovieListUseCase
import com.demo.codingassignmentlloyds.presentation.ViewState
import com.demo.codingassignmentlloyds.presentation.viewmodel.MovieListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieListUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: MovieRepositoryImpl

    private lateinit var useCase: MovieListUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testFetchDataSuccess() = runBlocking {
        val flow = flow {
            emit(Result.Success(emptyList<Movie>()))
        }
        Mockito.`when`(repository.getMovieList()).thenReturn(flow)
        useCase = MovieListUseCase(repository, testDispatcher)
        val response = useCase.fetchData().first()
        Assert.assertTrue( response is Result.Success<*>)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testFetchDataFail() = runBlocking {
        val flow = flow {
            emit(Result.Failure(Throwable("Error")))
        }
        Mockito.`when`(repository.getMovieList()).thenReturn(flow)
        useCase = MovieListUseCase(repository, testDispatcher)
        val response = useCase.fetchData().first()
        Assert.assertTrue( response is Result.Failure)
    }


}