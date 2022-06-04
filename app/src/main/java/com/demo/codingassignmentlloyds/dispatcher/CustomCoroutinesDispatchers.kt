package com.demo.codingassignmentlloyds.dispatcher

import kotlinx.coroutines.Dispatchers

class CustomCoroutinesDispatchers  : CoroutinesDispatchers {
    override fun io() = Dispatchers.IO

    override fun mainThread() = Dispatchers.Main

    override fun computation() = Dispatchers.Default
}