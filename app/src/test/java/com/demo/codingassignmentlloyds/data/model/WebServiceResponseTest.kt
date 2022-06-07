package com.demo.codingassignmentlloyds.data.model

import com.demo.codingassignmentlloyds.domain.datamodel.Result
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import java.io.IOException

class WebServiceResponseTest {

    @Test
    fun testOnSuccess(){
        val mockMovieItemsListResponse = mock(MovieItemsListResponse::class.java)
        val success = Result.Success(mockMovieItemsListResponse)
        Assert.assertTrue(success is Result.Success)
    }

    @Test
    fun testOnFailure() {
        val error = Result.Failure(IOException("error message"))
        Assert.assertTrue(error is Result.Failure)
    }
}