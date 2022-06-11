package com.demo.codingassignmentlloyds.presentation.view.userinterface

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.demo.codingassignmentlloyds.R
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.demo.codingassignmentlloyds.presentation.view.viewholder.MovieViewHolder
import com.demo.codingassignmentlloyds.utility.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentTest {

    val INDEX = 0
    lateinit var recyclerView: ViewInteraction
    lateinit var listFragment: ViewInteraction
    lateinit var detailFragment: ViewInteraction
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        recyclerView = onView(withId(R.id.rv_Movie))
        listFragment = onView(withId(R.id.fragment_movie_list))
        detailFragment = onView(withId(R.id.fragment_movie_detail))
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }


    @Test
    fun testIfFragmentIsLoaded() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        val titleScenario = launchFragmentInContainer<MovieListFragment>()

        titleScenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.nav_graph)
            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        listFragment.check(matches(isDisplayed()))
    }

    @Test
    fun testIfUserOnItemNavigateToDetailFragment() {
        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition<MovieViewHolder>(
            INDEX, ViewActions.click())
        )
        detailFragment.check(matches(isDisplayed()))
    }

    @Test
    fun testIfBackFromDetailFragmentLandOnList() {
        detailFragment.check(matches(isDisplayed()))
        pressBack()
        listFragment.check(matches(isDisplayed()))
    }

    @Test
    fun testIfRecyclerViewIsVisible() {
        recyclerView.check(
            matches(withEffectiveVisibility(VISIBLE))
        )
    }
}