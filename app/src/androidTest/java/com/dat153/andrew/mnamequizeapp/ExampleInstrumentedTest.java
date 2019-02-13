
package com.dat153.andrew.mnamequizeapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.TextView;

import com.dat153.andrew.mnamequizeapp.activities.WhoIsWhoActivity;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExampleInstrumentedTest {



    @Rule
    public ActivityTestRule<WhoIsWhoActivity> mWhoIsWhoActivity = new ActivityTestRule<>(WhoIsWhoActivity.class);


    //@Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.dat153.andrew.mnamequizeapp", appContext.getPackageName());

    }



    //@Test
    public void aButtonNotVisibleTest(){

        onView(withId(R.id.btnRestart))
                .check(doesNotExist());
    }


    //@Test
    public void addOwnerTest(){



        // click on editText field of AddOwner, type "hello", close the keyboard
        onView(withId(R.id.editText_ownerName)). perform(typeText("hello"), closeSoftKeyboard());

        // Click "SUBMIT"
        onView(withText("SUBMIT")).perform(click());

        // Check if it will show up
        onView(withId(R.id.textView_showOwnerName)).check(matches(isDisplayed()));

        // Show TextView if matches
        onView(withId(R.id.textView_showOwnerName)).check(matches(withText("hello")));

        //
        onView(withId(R.id.textView_showOwnerName)).check(matches(not(withText("Nihao"))));

    }

    //@Test
    public void attemptIncreasedTest() throws InterruptedException {

        onView(withId(R.id.editText_ownerName)). perform(typeText("hello"), closeSoftKeyboard());

        // Click "SUBMIT"
        onView(withText("SUBMIT")).perform(click());

        onView(withId(R.id.btnStart_Pause_Start)).perform(click());
        // Show TextView if matches
        onView(withId(R.id.textView_showOwnerName)).check(matches(withText("hello")));


        onView(withId(R.id.editText_guessedName)). perform(typeText("someTexts"), closeSoftKeyboard());

        onView(withText("Submit")).perform(click());

        onView(withId(R.id.textView_score)).check(matches(withText("Score: 0")));
        onView(withId(R.id.textView_attempt)).check(matches(withText("Attempts: 1")));


//        onView(withId(R.id.editText_guessedName)). perform(typeText("someTexts"), closeSoftKeyboard());
//        onView(withId(R.id.textView_showImgName)).check(matches(withId(R.id.editText_guessedName)));
//
//        onView(withId(R.id.textView_attempt)).check(matches(withText("Score: 1")));
//        onView(withId(R.id.textView_score)).check(matches(withText("Attempts: 2")));
    }


    //@Test
    public void scoreIncreasedTest(){




        onView(withId(R.id.editText_ownerName)). perform(typeText("OwnerName"), closeSoftKeyboard());

        // Click "SUBMIT"
        onView(withText("SUBMIT")).perform(click());
        //start quiz
        onView(withId(R.id.btnStart_Pause_Start)).perform(click());
        // Show TextView if matches
        //onView(withId(R.id.textView_showOwnerName)).check(matches(withText("OwnerName")));


        onView(withId(R.id.editText_guessedName)). perform(typeText("image"), closeSoftKeyboard());

        onView(withText("Submit")).perform(click());


        /**
         *   Failed to get String text from R.id.textView_showImgName
         */
        // onView(withId(R.id.editText_guessedName)).check(matches(withText(R.id.textView_showImgName)));

        onView(withId(R.id.textView_score)).check(matches(withText("Score: 1")));

    }

    @Test //This gives error because the answer in the textfield isnt removes/reset after an attempt. Easy to fix.
    public void correctScoreTest() {

        int expectedScore = 0;

        onView(withId(R.id.editText_ownerName)). perform(typeText("hello"), closeSoftKeyboard());

        // Click "SUBMIT"
        onView(withText("SUBMIT")).perform(click());
        //click start
        onView(withId(R.id.btnStart_Pause_Start)).perform(click());


        // Get the textview which contains the correct name related to the image
        TextView imgNameView = mWhoIsWhoActivity.getActivity().findViewById(R.id.textView_showImgName);

        //Type in correct name
        onView(withId(R.id.editText_guessedName)). perform(typeText(imgNameView.getText().toString()), closeSoftKeyboard());
        //submit the correct name which should give +1 score
        onView(withText("Submit")).perform(click());
        onView(withId(R.id.textView_attempt)).check(matches(withText("Attempts: 1")));
        expectedScore++;


        //Type in correct name for next question
        onView(withId(R.id.editText_guessedName)). perform(typeText(imgNameView.getText().toString()), closeSoftKeyboard());
        //submit correct name which should give +1 score
        onView(withText("Submit")).perform(click());
        onView(withId(R.id.textView_attempt)).check(matches(withText("Attempts: 2")));
        expectedScore++;

        //check if the expected score actually matches the displayed score
        TextView scoreView = mWhoIsWhoActivity.getActivity().findViewById(R.id.textView_score);
        //assertEquals(expectedScore, Integer.parseInt(scoreView.getText().toString().substring(7)));


    }





}
