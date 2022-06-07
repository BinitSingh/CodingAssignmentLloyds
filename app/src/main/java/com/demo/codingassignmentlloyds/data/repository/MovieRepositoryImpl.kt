package com.demo.codingassignmentlloyds.data.repository

import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.domain.datamodel.Result
import com.demo.codingassignmentlloyds.data.webservice.IDataSource
import com.demo.codingassignmentlloyds.domain.EntityMapper
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiCall: IDataSource,
    private val entityMapper: EntityMapper<MovieItemsListResponse, List<Movie>>
    ) : IMovieRepository {

    override suspend fun getMovieList(): Flow<Result<List<Movie>>> {
        return apiCall.getMovieList().map {
            when (it) {
                is Result.Success -> {
                    Result.Success(entityMapper.transformFrom(it.data))
                }
                is Result.Failure -> {
                    it
                }
            }
        }
    }
}