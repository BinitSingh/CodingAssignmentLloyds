package com.llyods.assignment


import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
import com.demo.codingassignmentlloyds.presentation.viewmodel.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

suspend fun <T : Any> getViewStateFlow(ioOperation: suspend () -> Flow<WebServiceResponse<T>>) =
    flow {
        emit(ViewState.Loading(true))
        ioOperation().map {
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
    }.flowOn(Dispatchers.IO)




