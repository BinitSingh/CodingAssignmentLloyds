package com.demo.codingassignmentlloyds.domain.repository

import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getMovieList(): Flow<WebServiceResponse<MovieItemsListResponse>>

    suspend fun transformData(response: MovieItemsListResponse): List<Movie>
}