package com.demo.codingassignmentlloyds.data.webservice

import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.domain.datamodel.Result
import com.demo.codingassignmentlloyds.dispatcher.CoroutinesDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val client: MovieApi,
    private val dispatcher: CoroutinesDispatchers
): IDataSource {

    override suspend fun getMovieList(): Flow<Result<MovieItemsListResponse>> {
        return flow {
            try {
                val response = client.fetchMovieList()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Result.Success(it))
                    } ?:
                    emit(Result.Failure(IOException(response.message())))
                    return@flow
                } else {
                    emit(Result.Failure(IOException()))
                    return@flow
                }
            }catch (exception: Exception){
                emit(Result.Failure(exception))
            }
        }.flowOn(dispatcher.io())
    }
}