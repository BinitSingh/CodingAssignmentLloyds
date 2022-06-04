package com.demo.codingassignmentlloyds.domain.usecase

import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.domain.repository.IMovieRepository
import com.demo.codingassignmentlloyds.domain.EntityMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieListUseCase @Inject constructor (private val repository: IMovieRepository):
    IUseCase<Any?, List<Movie>>, EntityMapper<MovieItemsListResponse, List<Movie>> {

    override suspend fun fetchData(input: Any?): Flow<WebServiceResponse<List<Movie>>> {
        return  flow {
            val webServiceResponse = repository.getMovieList()
            webServiceResponse.map { response ->
                when(response) {
                    is WebServiceResponse.OnSuccess -> {
                        WebServiceResponse.OnSuccess(transform(response.data))
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

    override fun transform(entity: MovieItemsListResponse): List<Movie> {
        val list = mutableListOf<Movie>()
        entity.items?.forEach {
            list.add(
                Movie(
                    id = it.id,
                    title = it.title,
                    year = it.year,
                    image = it.image,
                    crew = it.crew,
                    imDbRating = it.imDbRating,
                    imDbRatingCount = it.imDbRatingCount
                )
            )
        }
        return list
    }
}