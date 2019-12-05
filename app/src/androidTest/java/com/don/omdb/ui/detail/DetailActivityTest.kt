package com.don.omdb.ui.detail

import android.content.Intent

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry

import androidx.test.rule.ActivityTestRule
import com.don.omdb.R
import com.don.omdb.utils.FakeDataDummy
import org.junit.Rule
import org.junit.Test

/**
 * Created by gideon on 05,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
class DetailActivityTest {
    private val dummyMovie = FakeDataDummy.generateDummyDetail()

    @get:Rule
    var activityRule: ActivityTestRule<DetailActivity> = object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailActivity::class.java)
            result.putExtra(DetailActivity.EXTRA_IMDB, dummyMovie.imdbID)
            return result
        }
    }

    @Test
    fun loadCourse() {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.tvMovieTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvMovieTitle)).check(matches(withText(dummyMovie.title)))

        onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDate)).check(matches(withText(dummyMovie.year)))

//        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
//        onView(withId(R.id.text_date)).check(matches(withText(String.format("Deadline %s", dummyCourse.getDeadline()))))
    }

}