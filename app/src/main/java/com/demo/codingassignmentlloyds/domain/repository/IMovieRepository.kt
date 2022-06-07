package com.demo.codingassignmentlloyds.domain.repository

import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.datamodel.Result
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getMovieList(): Flow<Result<List<Movie>>>
}