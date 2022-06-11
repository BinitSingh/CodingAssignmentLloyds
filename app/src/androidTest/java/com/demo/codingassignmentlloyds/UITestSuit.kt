package com.demo.codingassignmentlloyds

import com.demo.codingassignmentlloyds.presentation.view.userinterface.MainActivityTest
import com.demo.codingassignmentlloyds.presentation.view.userinterface.MovieDetailFragmentTest
import com.demo.codingassignmentlloyds.presentation.view.userinterface.MovieListFragment
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    MovieListFragment::class,
    MovieDetailFragmentTest::class
)
class UITestSuit