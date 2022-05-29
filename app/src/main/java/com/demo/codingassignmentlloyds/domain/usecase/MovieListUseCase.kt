package com.demo.codingassignmentlloyds.domain.usecase

import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.repository.IMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieListUseCase @Inject constructor (private val repository: IMovieRepository):
    IUseCase<Any?, List<Movie>> {

    override suspend fun invoke(input: Any?): Flow<WebServiceResponse<List<Movie>>> {
        return  flow {
            val webServiceResponse = repository.getMovieList()
            webServiceResponse.map { response ->
                when(response) {
                    is WebServiceResponse.OnSuccess -> {
                        WebServiceResponse.OnSuccess(repository.transformData(response.data))
                    }
                    is WebServiceResponse.OnFailure -> {
                        WebServiceResponse.OnFailure(response.throwable)
                    }
                }
            }.collect {
                emit(it)
            }
            return@flow
        }.flowOn(Dispatchers.Default)
    }
}