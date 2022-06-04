package com.demo.codingassignmentlloyds.domain.repository

import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getMovieList(): Flow<WebServiceResponse<MovieItemsListResponse>>
}