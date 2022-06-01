package com.demo.codingassignmentlloyds.data.model

import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import java.io.IOException

class WebServiceResponseTest {

    @Test
    fun testOnSuccess(){
        val movieItemsListResponse = mockk<MovieItemsListResponse>()
        val success = WebServiceResponse.OnSuccess(movieItemsListResponse)
        Assert.assertTrue(success is WebServiceResponse.OnSuccess)
    }

    @Test
    fun testOnFailure() {
        val error = WebServiceResponse.OnFailure(IOException("error message"))
        Assert.assertTrue(error is WebServiceResponse.OnFailure)
    }
}