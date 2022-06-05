package com.demo.codingassignmentlloyds.domain.usecase

import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
import com.demo.codingassignmentlloyds.dispatcher.CoroutinesDispatchers
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.repository.IMovieRepository
import com.demo.codingassignmentlloyds.domain.EntityMapper
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieListUseCase @Inject constructor (
    private val repository: IMovieRepository,
    private val dispatcher: CoroutinesDispatchers,
    private val entityMapper: EntityMapper<MovieItemsListResponse, List<Movie>>
    ): IUseCase<Any?, List<Movie>> {

    override suspend fun fetchData(input: Any?): Flow<WebServiceResponse<List<Movie>>> {
        return  flow {
            val webServiceResponse = repository.getMovieList()
            webServiceResponse.map { response ->
                when(response) {
                    is WebServiceResponse.OnSuccess -> {
                        WebServiceResponse.OnSuccess(entityMapper.transformFrom(response.data))
                    }
                    is WebServiceResponse.OnFailure -> {
                        WebServiceResponse.OnFailure(response.throwable)
                    }
                }
            }.collect {
                emit(it)
            }
            return@flow
        }.flowOn(dispatcher.computation())
    }
}