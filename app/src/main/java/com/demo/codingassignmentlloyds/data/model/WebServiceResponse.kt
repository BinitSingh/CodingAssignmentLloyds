package com.demo.codingassignmentlloyds.data.model

sealed class WebServiceResponse<out T : Any> {
    data class OnSuccess<out T : Any>(val data: T) : WebServiceResponse<T>()
    data class OnFailure(val throwable: Throwable) : WebServiceResponse<Nothing>()
}