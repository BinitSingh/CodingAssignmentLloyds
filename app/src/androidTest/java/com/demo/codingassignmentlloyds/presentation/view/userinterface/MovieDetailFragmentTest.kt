package com.demo.codingassignmentlloyds.presentation.view.userinterface
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.demo.codingassignmentlloyds.R
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testIfDetailFragmentIsLoaded() {
        onView(withId(R.id.fragment_movie_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun testIfMovieImageIsVisible() {
        onView(withId(R.id.img_banner)).check(
            matches(withEffectiveVisibility(VISIBLE))
        )
    }

}