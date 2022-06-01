package com.demo.codingassignmentlloyds.data.webservice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.codingassignmentlloyds.TestCoroutineRule
import com.demo.codingassignmentlloyds.MockFileReader
import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
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

class RetrofitClientTest {
    lateinit var mockWebServer: MockWebServer
    @get:Rule
    var mainCoroutineRule = TestCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var webClient: RetrofitClient

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
            .create(RetrofitClient::class.java)
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
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
    fun TestMovieListFromServer() {
        runBlocking {
            mockResponseFromJson("/MovieItemsListResponse.json")
            val movieListItemResponse = webClient.getMovieList()
            val webServiceResponse = WebServiceResponse.OnSuccess(movieListItemResponse)
            val movieList = webServiceResponse.data.body()?.items
            Assert.assertEquals(movieList?.get(0)?.id, "tt1745960")
            Assert.assertEquals(movieList?.size, 100)
        }
    }
}