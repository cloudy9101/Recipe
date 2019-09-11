package com.cloudy9101.weltec.recipe;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
public class IngredientsTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp(){
        //Opens the ingredients fragment
        onView(withId(R.id.navigation_ingredients)).perform(click());

        //Checks ingredients fragment is displayed
        onView(withId(R.id.navigation_ingredients)).check(matches(isDisplayed()));
    }

    @Test
    public void testInsertIngredient(){
        //Click ingredient text input
        onView(withId(R.id.ingredientInput)).perform(click());

        //Enter text
        onView(withId(R.id.ingredientInput)).perform(typeText("egg"));

        //Click 'add' to insert ingredient
        onView(withId(R.id.addBtn)).perform(click());

        //Check item in list matches entered text
        ViewInteraction textView = onView(allOf(withId(R.id.name), withText("egg"),
                        childAtPosition(childAtPosition(withId(R.id.ingredientList),0),
                                0), isDisplayed()));
        textView.check(matches(withText("egg")));
    }

    @Test
    public void testSearchByIngredient(){

        onView(withId(R.id.ingredientInput)).perform(click());
        onView(withId(R.id.ingredientInput)).perform(typeText("egg"));
        onView(withId(R.id.addBtn)).perform(click());

        //Navigate to 'Recipes' page
        onView(withId(R.id.navigation_recipes)).perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){

        }

        //Click the button to search by ingredient
        onView(withId(R.id.intelligentBtn)).perform(click());

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e){

        }

        //Check items in list contain ingredient
        onData(anything()).inAdapterView(withId(R.id.recipeList)).atPosition(0)
                .onChildView(withId(R.id.name))
                .check(matches(withText(containsString("Eggs"))));
    }

    @After
    public void testDeleteIngredient(){
        onView(withId(R.id.navigation_ingredients)).perform(click());

        //Click delete button
        onView(withId(R.id.delBtn)).perform(click());

        //Check ingredients list is empty
        onView(withId(R.id.ingredientList)).check(matches(listSize(0)));
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

    public static Matcher<View> listSize (final int size) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(final View view) {
                return ((RecyclerView) view).getChildCount() == size;
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }
}

