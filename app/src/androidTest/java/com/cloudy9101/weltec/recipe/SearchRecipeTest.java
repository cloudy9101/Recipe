package com.cloudy9101.weltec.recipe;


import android.view.View;
import android.widget.ListView;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.startsWith;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SearchRecipeTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        //Opens up the recipes fragment
        onView(withId(R.id.navigation_recipes)).perform(click());

        //Checks recipes fragment is displayed
        onView(withId(R.id.navigation_recipes)).check(matches(isDisplayed()));
    }

    @Test
    public void testSearchRecipe() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){

        }

        //Click the search box
        onView(withId(R.id.search)).perform(click());

        //Type a string into search box
        onView(withId(R.id.search)).perform(typeText("chicken"));

        //Click search button
        onView(withId(R.id.searchBtn)).perform(click());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e){

        }

        //Verifies that the first item in the list contains the word 'chicken'
        onData(anything()).inAdapterView(withId(R.id.recipeList)).atPosition(0)
                .onChildView(withId(R.id.name))
                .check(matches(withText(startsWith("Chicken"))));

        //Verifies that the list size is 10
        onView(withId(R.id.recipeList)).check(matches(listSize(10)));
    }

    @Test
    public void testInvalidText(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){

        }

        //Click the search box
        onView(withId(R.id.search)).perform(click());

        //Type a string into search box
        onView(withId(R.id.search)).perform(typeText("technology"));

        //Click search button
        onView(withId(R.id.searchBtn)).perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){

        }

        //Verifies list size is 0
        onView(withId(R.id.recipeList)).check(matches(listSize(0)));
    }

    public static Matcher<View> listSize (final int size){
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(final View view) {
                return ((ListView) view).getCount() == size;
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }


}



