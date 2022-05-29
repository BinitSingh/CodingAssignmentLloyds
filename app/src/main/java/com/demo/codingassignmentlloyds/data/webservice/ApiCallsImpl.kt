package com.demo.codingassignmentlloyds.data.webservice

import com.demo.codingassignmentlloyds.data.model.MovieDetailResponse
import com.demo.codingassignmentlloyds.data.model.MovieItemsListResponse
import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ApiCallsImpl @Inject constructor(private val client: RetrofitClient): IApiCalls {
    override suspend fun getMovieList(): Flow<WebServiceResponse<MovieItemsListResponse>> =
        performNetworkApiCall { client.getMovieList() }

    override suspend fun getMovieDetail(id: String?): Flow<WebServiceResponse<MovieDetailResponse>> =
        performNetworkApiCall { client.getMovieDetailResponse(movieId = id) }


    private suspend fun <T : Any> performNetworkApiCall(
        networkApiCall: suspend () -> Response<T>
    ): Flow<WebServiceResponse<T>> {
        return flow {
            val response = networkApiCall()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(WebServiceResponse.OnSuccess(it))
                }
                    ?: emit(WebServiceResponse.OnFailure(IOException("API call successful but empty response body")))
                return@flow
            }
            emit(
                WebServiceResponse.OnFailure(
                    IOException(
                        "API call failed with error - ${response.errorBody()?.toString() ?: ""}"
                    )
                )
            )
            return@flow
        }.catch { exception ->
            emit(
                WebServiceResponse.OnFailure(
                    IOException("Exception during network API call: ${exception.message}")
                )
            )
            return@catch
        }.flowOn(Dispatchers.IO)
    }

}