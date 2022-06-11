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
//import com.demo.codingassignmentlloyds.domain.datamodel.Result
//import com.demo.codingassignmentlloyds.domain.usecase.MovieListUseCase
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
//class MovieListViewModalTest {
//
//    @get:Rule
//    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
//
//    @get:Rule
//    val testCoroutineRule = MainCoroutineRule()
//    @Mock
//    private lateinit var useCase: MovieListUseCase
//
//    @Mock
//    private lateinit var apiHelper: MovieApi
//
//    @Mock
//    private lateinit var repository: MovieRepositoryImpl
//
//    @Before
//    fun setUp(){
//        MockitoAnnotations.initMocks(this)
//    }
//
//    private val testDispatcher = TestCoroutineDispatcher()
//    @Test
//    fun fetchMovieListTest() = runBlocking {
//        val viewModel = MovieListViewModel(useCase, testDispatcher)
//
//        val fileName = "/MovieItemsListResponse.json"
//        val json = MockFileReader().getResponseFromJson(fileName)
//        val movieItemsListResponse = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
//            .adapter(MovieItemsListResponse::class.java).fromJson(json)
//        val movieList =  MovieListMapper().transformFrom(movieItemsListResponse!!)
//        val channel = Channel<Result<List<Movie>>>()
//        val flow = channel.consumeAsFlow()
//        Mockito.`when`(useCase.fetchData()).thenReturn(flow )
//        val job = launch {
//            channel.send(Result.Success(movieList))
//        }
//        viewModel.fetchMovieList()
//        viewModel.getViewStateFlow().collect {
//            when (it) {
//                is ViewState.Loading -> {
//                    Assert.assertEquals(it.isLoading , true)
//                }
//                is ViewState.Success -> {
//                    Assert.assertEquals(it.result.size , 100)
//                }
//            }
//        }
//        job.cancel()
//    }
//
//
//    val stateFlow = MutableStateFlow(ViewState.Success(emptyList<Movie>()))
//
//}
