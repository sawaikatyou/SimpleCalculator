/*
 * *
 *  ActivityTest
 *
 *  Copyright (c) 2018. sasakik
 *
 *  This software is released under the MIT License.
 *  http://opensource.org/licenses/mit-license.php
 *
 */

package com.sasakik.simplecalculator;

import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

/**
 * Created by mega_ on 2018/02/04.
 */

public class SimpleCalculatorActivityTest {
    @Rule
    public ActivityTestRule<SimpleCalculatorActivity> activityTestRule = new ActivityTestRule<>(SimpleCalculatorActivity.class, false, false);

    @Test
    public void check_start_activity() {
        SimpleCalculatorActivity activity = activityTestRule.launchActivity(null);
        /* ここにテストする内容を書く */
        assertThat(
                "SimpleCalculatorActivity is running",
                activity.isFinishing(),
                Matchers.is(false)
        );

        /* 1 と打ち込んだらテキストに"1"と出てくる */
        onView(withId(R.id.one)).perform(click());
        onView(withId(R.id.disp_num)).check(matches(withText("1")));

        /* "AC" でクリア */
        onView(withId(R.id.disp_num)).check(matches(not(withText("0"))));
        onView(withId(R.id.all_clear)).perform(click());
        onView(withId(R.id.disp_num)).check(matches(withText("0")));

        /* "1.2" + "2.8" = "4" */
        onView(withId(R.id.one)).perform(click());
        onView(withId(R.id.dot)).perform(click());
        onView(withId(R.id.two)).perform(click());
        onView(withId(R.id.disp_num)).check(matches(withText("1.2")));
        onView(withId(R.id.plus)).perform(click());
        onView(withId(R.id.two)).perform(click());
        onView(withId(R.id.dot)).perform(click());
        onView(withId(R.id.eight)).perform(click());
        onView(withId(R.id.disp_num)).check(matches(withText("2.8")));
        onView(withId(R.id.equal)).perform(click());
        onView(withId(R.id.disp_num)).check(matches(withText("4.0")));

    }

}
