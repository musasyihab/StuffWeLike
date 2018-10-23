package com.musasyihab.stuffwelike;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.musasyihab.stuffwelike.ui.selection.SelectionActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class SelectionActivityTest {

    private Context context;

    @Before
    public void setContext() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Rule
    public ActivityTestRule<SelectionActivity> mActivityTestRule = new ActivityTestRule<>(SelectionActivity.class);


    @Test
    public void testLoadArticles() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction counterView = onView(
                allOf(withId(R.id.like_counter),
                        isDisplayed()));
        counterView.check(matches(isDisplayed()));

        ViewInteraction flipperView = onView(
                allOf(withId(R.id.article_flipper),
                        isDisplayed()));
        flipperView.check(matches(isDisplayed()));

        mActivityTestRule.finishActivity();

    }

}
