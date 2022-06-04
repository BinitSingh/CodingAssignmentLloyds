package com.demo.codingassignmentlloyds.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
import com.demo.codingassignmentlloyds.dispatcher.CoroutinesDispatchers
import com.demo.codingassignmentlloyds.presentation.ViewState
import kotlinx.coroutines.flow.*

open class BaseViewModel(private val dispatchers: CoroutinesDispatchers) : ViewModel() {
    suspend fun <T : Any> getViewStateFlowFromWebServiceResponse(
        response: Flow<WebServiceResponse<T>>,
    ) : Flow<ViewState<T>> {
        return flow {
            emit(ViewState.Loading(true))
            response.map {
                when (it) {
                    is WebServiceResponse.OnSuccess ->
                        ViewState.Success(it.data)
                    is WebServiceResponse.OnFailure ->
                        ViewState.Failure(it.throwable)
                }
            }.collect {
                emit(it)
            }
            emit(ViewState.Loading(false))
        }.flowOn(dispatchers.computation())
    }
}


