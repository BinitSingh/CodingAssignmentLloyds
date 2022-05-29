package com.demo.codingassignmentlloyds.domain.repository

import com.demo.codingassignmentlloyds.data.model.MovieDetailResponse
import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.datamodel.MovieDetail
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getMovieList(): Flow<WebServiceResponse<MovieItemsListResponse>>

    suspend fun getMovieDetail(id: String?): Flow<WebServiceResponse<MovieDetailResponse>>

    suspend fun transformData(response: MovieItemsListResponse): List<Movie>

    suspend fun transformData(response: MovieDetailResponse): MovieDetail
}