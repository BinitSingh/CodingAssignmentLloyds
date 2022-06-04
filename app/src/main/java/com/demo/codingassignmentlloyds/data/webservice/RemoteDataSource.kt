package com.demo.codingassignmentlloyds.data.webservice

import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
import com.demo.codingassignmentlloyds.dispatcher.CoroutinesDispatchers
import com.demo.codingassignmentlloyds.dispatcher.CustomCoroutinesDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val client: MovieApi,
    private val dispatcher: CoroutinesDispatchers
): IDataSource {

    override suspend fun getMovieList(): Flow<WebServiceResponse<MovieItemsListResponse>> {
        return flow {
            try {
                val response = client.fetchMovieList()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(WebServiceResponse.OnSuccess(it))
                    } ?:
                    emit(WebServiceResponse.OnFailure(IOException(response.message())))
                    return@flow
                } else {
                    emit(WebServiceResponse.OnFailure(IOException()))
                    return@flow
                }
            }catch (exception: Exception){
                emit(WebServiceResponse.OnFailure(exception))
            }
        }.flowOn(dispatcher.io())
    }
}