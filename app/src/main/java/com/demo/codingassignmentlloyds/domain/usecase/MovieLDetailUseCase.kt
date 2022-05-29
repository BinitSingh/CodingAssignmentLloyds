package com.demo.codingassignmentlloyds.domain.usecase

import com.demo.codingassignmentlloyds.data.model.MovieDetailResponse
import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.datamodel.MovieDetail
import com.demo.codingassignmentlloyds.domain.repository.IMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieLDetailUseCase @Inject constructor (private val repository: IMovieRepository)
    :IUseCase<String?, MovieDetail> {
    override suspend fun invoke(input: String?): Flow<WebServiceResponse<MovieDetail>> {
        return  flow {
            val webServiceResponse = repository.getMovieDetail(input)
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