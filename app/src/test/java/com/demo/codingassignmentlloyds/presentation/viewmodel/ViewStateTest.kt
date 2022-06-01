package com.demo.codingassignmentlloyds.presentation.viewmodel

import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import java.io.IOException


class ViewStateTest {
    @Test
    fun testLoadingViewState(){
        val loading = ViewState.Loading(true)
        Assert.assertTrue(loading is ViewState.Loading)
    }

    @Test
    fun testSuccessViewState(){
        val movieItemsListResponse = mockk<MovieItemsListResponse>()
        val success = ViewState.Success(movieItemsListResponse)
        Assert.assertTrue(success is ViewState.Success)
    }

    @Test
    fun testFailureViewState() {
        val error = ViewState.Failure(Exception("Some error"))
        Assert.assertTrue(error is ViewState.Failure)
    }
}