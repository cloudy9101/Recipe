package com.cloudy9101.weltec.recipe;

import androidx.test.espresso.intent.Intents;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.release;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivity = new ActivityTestRule(LoginActivity.class);

    //--------------Sign up Tests-----------------
    @Test
    public void testSignupNoEmail(){

        //Checking no email has been entered
        onView(withId(R.id.email)).check(ViewAssertions.matches(withText("")));

        //Clicking the sign up button
        onView(withId(R.id.signup)).perform(click());

        //Checking that the toast message matches text
        onView(withText("Enter email address!"))
                .inRoot(withDecorView(not(is(loginActivity.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testSignupNoPassword(){
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
    public void testSignupInvalidEmail(){

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
    public void testSignupInvalidPassword(){

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
        onView(withId(R.id.email)).perform(typeText("johndoe@gmail.com"));

        //Entering valid password
        onView(withId(R.id.password)).perform(typeText("password"));

        //Clicking sign up button
        onView(withId(R.id.signup)).perform(click());

        //Checking that the MainActivity page opens
        intended(hasComponent(MainActivity.class.getName()));
        Intents.release();

        //Logout
        onView(withId(R.id.logoutBtn)).perform(click());
    }

    //--------------Login Tests-----------------

    @Test
    public void testLoginNoEmail(){

        //Checking no email has been entered
        onView(withId(R.id.email)).check(ViewAssertions.matches(withText("")));

        //Clicking the login button
        onView(withId(R.id.login)).perform(click());

        //Checking that the toast message matches text
        onView(withText("Enter email address!"))
                .inRoot(withDecorView(not(is(loginActivity.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testLoginNoPassword(){
        //Entering a valid email address
        onView(withId(R.id.email)).perform(typeText("johndoe@gmail.com"));

        //Clicking login button
        onView(withId(R.id.login)).perform(click());

        //Checking that the toast message matches text
        onView(withText("Password must more than 6 letters!"))
                .inRoot(withDecorView(not(is(loginActivity.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testLoginInvalidEmail(){

        //Entering invalid email
        onView(withId(R.id.email)).perform(typeText("john"));

        //Entering valid password
        onView(withId(R.id.password)).perform(typeText("password"));

        //Clicking login button
        onView(withId(R.id.login)).perform(click());

        //Checking that the toast message matches text
        onView(withText("Login failed"))
                .inRoot(withDecorView(not(is(loginActivity.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testLoginInvalidPassword(){

        //Entering valid email
        onView(withId(R.id.email)).perform(typeText("johndoea@gmail.com"));

        //Entering invalid password
        onView(withId(R.id.password)).perform(typeText("pass"));

        //Clicking login button
        onView(withId(R.id.login)).perform(click());

        //Checking that the toast message matches text
        onView(withText("Password must more than 6 letters!"))
                .inRoot(withDecorView(not(is(loginActivity.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testLoginUnregisteredEmailAndPassword() {

        //Entering valid email
        onView(withId(R.id.email)).perform(typeText("jdoe@gmail.com"));

        //Entering valid password
        onView(withId(R.id.password)).perform(typeText("password"));

        //Clicking login button
        onView(withId(R.id.login)).perform(click());

        //Checking that the toast message matches text
        onView(withText("Login failed"))
                .inRoot(withDecorView(not(is(loginActivity.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

    }

    @Test
    public void testLoginValidEmailAndPassword() {

        //Entering valid email
        onView(withId(R.id.email)).perform(typeText("johndoe@gmail.com"));

        //Entering valid password
        onView(withId(R.id.password)).perform(typeText("password"));

        //Clicking login button
        onView(withId(R.id.login)).perform(click());

        //Checking that the MainActivity page opens
        intended(hasComponent(MainActivity.class.getName()));
        release();
    }
}