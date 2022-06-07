package com.demo.codingassignmentlloyds.domain.usecase

import com.demo.codingassignmentlloyds.domain.datamodel.Result
import com.demo.codingassignmentlloyds.dispatcher.CoroutinesDispatchers
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieListUseCase @Inject constructor (
    private val repository: IMovieRepository,
    private val dispatcher: CoroutinesDispatchers
    ): IUseCase<Any?, List<Movie>> {

    override suspend fun fetchData(input: Any?): Flow<Result<List<Movie>>> =
          flow {
            val response = repository.getMovieList()
              response.map {
                when(it) {
                    is Result.Success -> {
                        Result.Success(it.data)
                    }
                    is Result.Failure -> {
                        Result.Failure(it.throwable)
                    }
                }
            }.collect {
                emit(it)
            }
            return@flow
        }.flowOn(dispatcher.computation())

}