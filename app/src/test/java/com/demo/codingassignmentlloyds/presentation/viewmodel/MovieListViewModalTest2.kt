//package com.demo.codingassignmentlloyds.presentation.viewmodel
//
//import android.util.Log
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.demo.codingassignmentlloyds.MainCoroutineRule
//import com.demo.codingassignmentlloyds.MockFileReader
//import com.demo.codingassignmentlloyds.data.datamapper.MovieListMapper
//import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
//import com.demo.codingassignmentlloyds.data.repository.MovieRepositoryImpl
//import com.demo.codingassignmentlloyds.data.webservice.MovieApi
//import com.demo.codingassignmentlloyds.domain.datamodel.Movie
//import com.demo.codingassignmentlloyds.domain.usecase.MovieListUseCase
//import com.demo.codingassignmentlloyds.domain.datamodel.Result
//import com.demo.codingassignmentlloyds.presentation.ViewState
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.channels.Channel
//import kotlinx.coroutines.flow.*
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.runBlocking
//import kotlinx.coroutines.test.TestCoroutineDispatcher
//import kotlinx.coroutines.test.runBlockingTest
//import org.junit.Assert
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.rules.TestRule
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.mockito.MockitoAnnotations
//import org.mockito.junit.MockitoJUnitRunner
//import retrofit2.Response
//
//@RunWith(MockitoJUnitRunner::class)
//@ExperimentalCoroutinesApi
//class MovieListViewModalTest2 {
//
//    @get:Rule
//    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
//
//    @get:Rule
//    val testCoroutineRule = MainCoroutineRule()
//    @Mock
//    private lateinit var useCase: MovieListUseCase
//
//    private lateinit var viewModel: MovieListViewModel
//
////    @Mock
////    private lateinit var apiHelper: MovieApi
////
////    @Mock
////    private lateinit var repository: MovieRepositoryImpl
//    private val testDispatcher = TestCoroutineDispatcher()
//
//    @Before
//    fun setUp(){
//        MockitoAnnotations.initMocks(this)
//        viewModel = MovieListViewModel(useCase, testDispatcher)
//    }
//
//
//    @Test
//    fun fetchMovieListTest() = runBlocking {
//        Mockito.`when`(useCase.fetchData()).thenReturn(
//            flow {
//                emit(Result.Success(emptyList<Movie>()) )
//            }
//        )
//        viewModel.fetchMovieList()
//        Assert.assertEquals(
//            ViewState.Success(emptyList<Movie>()), viewModel.getViewStateFlow().value
//        )
//    }
//}
