package com.dat153.andrew.mnamequizeapp;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.dat153.andrew.mnamequizeapp.activities.WhoIsWhoActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddOwnerTest {


    private static final String mStringToBeTyped = "Espresso";

    @Rule
    public ActivityTestRule whoIsWhoActivitiyTestRule = new ActivityTestRule<>(WhoIsWhoActivity.class);


    @Test
    public void changeText_sameActivivity(){

        onView(withId(R.id.editText_ownerName))
                .perform(typeText(mStringToBeTyped), closeSoftKeyboard());
        onView(withText())

    }





}
