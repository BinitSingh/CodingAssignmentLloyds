package com.demo.codingassignmentlloyds.data.repository

import com.demo.codingassignmentlloyds.data.datamapper.MovieListMapper
import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
import com.demo.codingassignmentlloyds.data.webservice.IDataSource
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(val apiCall: IDataSource) :
    IMovieRepository {

    override suspend fun getMovieList(): Flow<WebServiceResponse<MovieItemsListResponse>> =
        apiCall.getMovieList()

}