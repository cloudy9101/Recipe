package com.cloudy9101.weltec.recipe;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.init;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
public class SignupActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivity = new ActivityTestRule(LoginActivity.class);


    @Before
    public void setup() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();
    }

    @Test
    public void testSignupNoEmail() {

        //Checking no email has been entered
        onView(withId(R.id.email)).check(matches(withText("")));

        //Clicking the sign up button
        onView(withId(R.id.signup)).perform(click());

        //Checking that the toast message matches text
        onView(withText("Enter email address!"))
                .inRoot(withDecorView(not(is(loginActivity.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testSignupNoPassword() {
        //Entering a valid email address
        onView(withId(R.id.email)).perform(typeText("johndoe@gmail.com"));

        //Clicking sign up button
        onView(withId(R.id.signup)).perform(click());

        //Checking that the toast message matches text
        onView(withText("Password must more than 6 letters!"))
                .inRoot(withDecorView(not(is(loginActivity.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testSignupInvalidEmail() {

        //Entering invalid email
        onView(withId(R.id.email)).perform(typeText("john"));

        //Entering valid password
        onView(withId(R.id.password)).perform(typeText("password"));

        //Clicking sign up button
        onView(withId(R.id.signup)).perform(click());

        //Checking that the toast message matches text
        onView(withText("Login failed"))
                .inRoot(withDecorView(not(is(loginActivity.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testSignupInvalidPassword() {

        //Entering valid email
        onView(withId(R.id.email)).perform(typeText("johndoe@gmail.com"));

        //Entering invalid password
        onView(withId(R.id.password)).perform(typeText("pass"));

        //Clicking sign up button
        onView(withId(R.id.signup)).perform(click());

        //Checking that the toast message matches text
        onView(withText("Password must more than 6 letters!"))
                .inRoot(withDecorView(not(is(loginActivity.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testSignupValidEmailAndPassword() {

        //Entering valid email
        onView(withId(R.id.email)).perform(typeText("john@gmail.com"));

        //Entering valid password
        onView(withId(R.id.password)).perform(typeText("password"));

        //Clicking sign up button
        onView(withId(R.id.signup)).perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){

        }

        //Checking that the MainActivity page opens
        onView(withId(R.id.navigation_recipes)).check(matches(isDisplayed()));

    }
}
