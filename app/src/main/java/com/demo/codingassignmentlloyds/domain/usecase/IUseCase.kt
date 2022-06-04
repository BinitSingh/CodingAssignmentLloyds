package com.demo.codingassignmentlloyds.domain.usecase

import com.demo.codingassignmentlloyds.data.model.WebServiceResponse
import kotlinx.coroutines.flow.Flow

interface IUseCase<in I : Any?, out T : Any>  {
    suspend  fun fetchData(
        input: I? = null
    ): Flow<WebServiceResponse<T>>
}