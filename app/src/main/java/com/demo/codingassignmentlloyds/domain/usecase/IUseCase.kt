package com.demo.codingassignmentlloyds.domain.usecase

import com.demo.codingassignmentlloyds.domain.datamodel.Result
import kotlinx.coroutines.flow.Flow

interface IUseCase<in I : Any?, out T : Any>  {
    suspend  fun fetchData(
        input: I? = null
    ): Flow<Result<T>>
}