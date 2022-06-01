package com.demo.codingassignmentlloyds.data.webservice

import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
import kotlinx.coroutines.flow.Flow

interface IApiCalls {
    suspend fun getMovieList(): Flow<WebServiceResponse<MovieItemsListResponse>>
}