package com.demo.codingassignmentlloyds.data.repository

import com.demo.codingassignmentlloyds.data.mapper.MoviesMapper.toMovieList
import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
import com.demo.codingassignmentlloyds.data.webservice.IApiCalls
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(val apiCall: IApiCalls) : IMovieRepository {

    override suspend fun getMovieList(): Flow<WebServiceResponse<MovieItemsListResponse>> =
        apiCall.getMovieList()

    override suspend fun transformData(response: MovieItemsListResponse): List<Movie> =
        response.toMovieList()

}