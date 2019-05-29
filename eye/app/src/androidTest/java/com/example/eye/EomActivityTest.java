package com.example.eye;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EomActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void eomActivityTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.eom),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction toggleButton = onView(
                allOf(withId(R.id.Button_pop_up_setting), withText("OFF"),
                        childAtPosition(
                                allOf(withId(R.id.Fragment_time),
                                        childAtPosition(
                                                withId(R.id.constraintLayout),
                                                2)),
                                1),
                        isDisplayed()));
        toggleButton.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.time_start_button),
                        childAtPosition(
                                allOf(withId(R.id.Fragment_time),
                                        childAtPosition(
                                                withId(R.id.constraintLayout),
                                                2)),
                                7),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("확인"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.add),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                6),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.EditText_lens_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.Add_ScrollView),
                                        0),
                                1)));
        appCompatEditText.perform(scrollTo(), replaceText("5유"), closeSoftKeyboard());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.period_left_onemonth), withText("1개월"),
                        childAtPosition(
                                allOf(withId(R.id.RadioGroup_lens_left),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        appCompatRadioButton.perform(scrollTo(), click());

        ViewInteraction appCompatRadioButton2 = onView(
                allOf(withId(R.id.period_right_onemonth), withText("1개월"),
                        childAtPosition(
                                allOf(withId(R.id.RadioGroup_lens_right),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                7)),
                                3)));
        appCompatRadioButton2.perform(scrollTo(), click());

        ViewInteraction appCompatRadioButton3 = onView(
                allOf(withId(R.id.period_right_onemonth), withText("1개월"),
                        childAtPosition(
                                allOf(withId(R.id.RadioGroup_lens_right),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                7)),
                                3)));
        appCompatRadioButton3.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.Button_add), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        8),
                                0)));
        appCompatButton2.perform(scrollTo(), click());

        pressBack();
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
