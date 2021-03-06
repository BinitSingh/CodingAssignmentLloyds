package com.demo.codingassignmentlloyds.data.webservice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.codingassignmentlloyds.MainCoroutineRule
import com.demo.codingassignmentlloyds.MockFileReader
import com.demo.codingassignmentlloyds.domain.datamodel.Result
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

class MovieApiTest {

    lateinit var mockWebServer: MockWebServer
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var webClient: MovieApi

    @Before
    fun initService(){
        mockWebServer = MockWebServer()
        mockWebServer.start()
        val moshi =  Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        webClient = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(MovieApi::class.java)
    }

    @Throws(IOException::class)
    fun mockResponseFromJson(fileName: String) {
        val mockResponse = MockResponse()
        mockWebServer.enqueue(
            mockResponse.setBody(
                MockFileReader().getResponseFromJson(fileName)
            )
        )
    }

    @Test
    fun testMovieListFromServer() {
        runBlocking {
            mockResponseFromJson("/MovieItemsListResponse.json")
            val movieListItemResponse = webClient.fetchMovieList()
            val webServiceResponse = Result.Success(movieListItemResponse)
            val movieList = webServiceResponse.data.body()?.items
            Assert.assertEquals(movieList?.get(0)?.id, "tt1745960")
            Assert.assertEquals(movieList?.size, 100)
        }
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}