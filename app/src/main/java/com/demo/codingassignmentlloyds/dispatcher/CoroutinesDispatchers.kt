package com.demo.codingassignmentlloyds.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutinesDispatchers {
    fun io(): CoroutineDispatcher
    fun mainThread(): CoroutineDispatcher
    fun computation(): CoroutineDispatcher
}