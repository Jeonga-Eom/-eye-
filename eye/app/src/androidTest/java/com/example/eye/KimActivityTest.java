package com.example.eye;


import android.support.test.espresso.DataInteraction;
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

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class KimActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void kimActivityTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.kim),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        0),
                                4),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.button_main_insert),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText_lefteye),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editText_lefteye),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("-."), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editText_righteye),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("/3.5"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.editText_righteye), withText("/3.5"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button_dialog_next), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        pressBack();

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.LensSearch),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText5.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.LensSearch),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("아큐브"), closeSoftKeyboard());

        DataInteraction appCompatTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.listview),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(2);
        appCompatTextView.perform(click());

        DataInteraction appCompatTextView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listview),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(0);
        appCompatTextView2.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.next2), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.radioButton_history), withText("History"),
                        childAtPosition(
                                allOf(withId(R.id.radiogroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatRadioButton.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.finish), withText("Finish"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.image),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerview_main_list),
                                        0),
                                0),
                        isDisplayed()));
        appCompatImageView.perform(click());

        pressBack();

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.image),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerview_main_list),
                                        0),
                                0),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        pressBack();

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerview_main_list),
                        childAtPosition(
                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                3)));
        recyclerView.perform(actionOnItemAtPosition(0, longClick()));

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("삭제"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.android.internal.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());
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
