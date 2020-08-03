package com.puneet.musicalwipro.albumsearch

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.puneet.musicalwipro.albumsearch.ui.MusicAlbumActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

const val sleepMilli = 4000L

@RunWith(AndroidJUnit4::class)
class SearchInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MusicAlbumActivity::class.java)

    @Before
    fun before() {

    }

    @Test
    fun searchNotFound() {

        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("FFFFFF AAAAAAAAA hhhhhhhhhh"),
            pressImeActionButton()
        )

        // see TO-DO above
        sleep(sleepMilli)
        onView(withId(R.id.no_results_found)).check(matches(isDisplayed()))
    }

    @Test
    fun searchResultFound() {

        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("test"),
            pressImeActionButton()
        )

        // see TO-DO above
        sleep(sleepMilli)
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun searchResultFoundAndNoResultViewIsNotDisplayed() {

        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("test"),
            pressImeActionButton()
        )

        // see TO-DO above
        sleep(sleepMilli)
        onView(withId(R.id.no_results_found)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @After
    fun after() {
        // unregister the mighty idlingResource (once it's registered
    }
}
