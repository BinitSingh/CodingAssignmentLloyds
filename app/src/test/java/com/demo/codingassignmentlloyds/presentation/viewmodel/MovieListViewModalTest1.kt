//package com.demo.codingassignmentlloyds.presentation.viewmodel
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.Observer
//import com.demo.codingassignmentlloyds.MainCoroutineRule
//import com.demo.codingassignmentlloyds.domain.datamodel.Movie
//import com.demo.codingassignmentlloyds.domain.datamodel.Result
//import com.demo.codingassignmentlloyds.domain.usecase.MovieListUseCase
//import com.demo.codingassignmentlloyds.presentation.ViewState
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.*
//import kotlinx.coroutines.test.TestCoroutineDispatcher
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.rules.TestRule
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito.inOrder
//import org.mockito.MockitoAnnotations
//import org.mockito.junit.MockitoJUnitRunner
//
//
//@RunWith(MockitoJUnitRunner::class)
//@ExperimentalCoroutinesApi
//class MovieListViewModalTest1 {
//
//    @get:Rule
//    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
//
//    @get:Rule
//    val testCoroutineRule = MainCoroutineRule()
//
//    @Mock
//    private lateinit var useCase: MovieListUseCase
//
////    @Mock
////    private lateinit var apiHelper: MovieApi
////
////    @Mock
////    private lateinit var repository: MovieRepositoryImpl
//
//    private val testDispatcher = TestCoroutineDispatcher()
//
//    private lateinit var viewModel: MovieListViewModel
//
//    @Mock
//    private lateinit var observer: Observer<ViewState<List<Movie>>>
//
//    private val fakeSuccessFlow = flow {
//        emit(Result.Success(emptyList<Movie>()))
//    }
//
//    @Before
//    fun setUp(){
//        MockitoAnnotations.initMocks(this)
//        viewModel = MovieListViewModel(useCase, testDispatcher)
//    }
//
//    @Test
//    fun fetchMovieListTest(){
//        viewModel.fetchMovieList()
//        val inOrder = inOrder(
//            observer.onChanged(ViewState.Loading(true)),
//            observer.onChanged(ViewState.Success(emptyList())),
//            observer.onChanged(ViewState.Loading(false))
//        )
//
//        println(inOrder)
//
//    }
//
//
////    @Test
////    fun fetchMovieListTest() = runBlocking {
////        val fileName = "/MovieItemsListResponse.json"
////        val json = MockFileReader().getResponseFromJson(fileName)
////        val movieItemsListResponse = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
////            .adapter(MovieItemsListResponse::class.java).fromJson(json)
////        val movieList =  MovieListMapper().transformFrom(movieItemsListResponse!!)
////
//////        Mockito.`when`(useCase.fetchData()).thenReturn(
//////            flow {
//////                emit(Result.Success(movieList))
//////            }
//////        )
////
////        viewModel.fetchMovieList()
////
////        val result = mutableListOf<ViewState<List<Movie>>>()
////        val job = launch {
////            viewModel.getViewStateFlow().toList(result) //now it should work
////        }
////
////        Assert.assertEquals(viewModel.getViewStateFlow(), ViewState.Success(movieList))//works, last value enmited
////        Assert.assertEquals(result.first() , ViewState.Loading(true)) //also works
////
////        job.cancel()
////    }
//}
