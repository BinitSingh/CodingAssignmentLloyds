package com.demo.codingassignmentlloyds

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runners.model.Statement
import kotlin.coroutines.ContinuationInterceptor

/**
 * Coroutine dispatcher in order to avoid the Main dispatcher that is Android Specific
 */
@ExperimentalCoroutinesApi
class MainCoroutineRule(
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher(), TestCoroutineScope by TestCoroutineScope(testDispatcher) {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}

//@ExperimentalCoroutinesApi
//class MainCoroutineRule  : TestRule {
//    private val testCoroutineDispatcher = TestCoroutineDispatcher()
//
//    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)
//    override fun apply(base: Statement?, description: Description?) = object : Statement() {
//        @Throws(Throwable::class)
//        override fun evaluate() {
//            Dispatchers.setMain(testCoroutineDispatcher)
//            base?.evaluate()
//            Dispatchers.resetMain()
//            testCoroutineScope.cleanupTestCoroutines()
//        }
//    }
//
//    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
//        testCoroutineScope.runBlockingTest { block() }
//}