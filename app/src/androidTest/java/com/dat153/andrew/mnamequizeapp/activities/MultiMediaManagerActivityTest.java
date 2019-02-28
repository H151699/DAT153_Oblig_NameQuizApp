package com.dat153.andrew.mnamequizeapp.activities;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.dat153.andrew.mnamequizeapp.R;
import com.dat153.andrew.mnamequizeapp.utils.Upload;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.Callable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MultiMediaManagerActivityTest {
    long countBefore;
    long countAfter;

    @Rule
    public ActivityTestRule<MultiMediaManagerActivity> activityTestRule = new ActivityTestRule<>(MultiMediaManagerActivity.class);
    public ActivityTestRule<ViewImageActivity> viewImageActivityTestRule = new ActivityTestRule<>(ViewImageActivity.class);


    @Test
    public void addEntryTest() {
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        //set imgUrl to URI of img file, set imgDescription to some txt

        MultiMediaManagerActivity act = activityTestRule.getActivity();

        //gets uri from resource file
        Resources resources = act.getResources();
        int textImgResId = R.drawable.testingimage;
        Uri imgUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(textImgResId) + '/' + resources.getResourceTypeName(textImgResId) + '/' + resources.getResourceEntryName(textImgResId) );

        //sets the needed variables in MultiMediManagerActivity to make a new entry
        act.setNewImageUri(imgUri);


        Log.d("ENTRYTEST", " Variables set.");
        Log.d("ENTRYTEST", "the URI: " + imgUri);

        //Number of entries before adding one
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //dataSnapshot.child("WHAT GOES HERE?");
                countBefore = dataSnapshot.child("uploads").getChildrenCount();
                Log.d("ENTRYTEST", "count before: " + countBefore);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ENTRYTEST", "onCancelled ran");
            }
        } );

        //Wait needed to get the results from database
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            Log.d("ENTRYTEST", e.toString());
        }



        //Fill in name and then submit
        onView(withId(R.id.imgDescription)).perform(click()).perform(typeText("imagetestname7"), closeSoftKeyboard());
        onView(withId(R.id.btnUploadImage)).perform(click());

        //wait needed to submit the entry to database
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            Log.d("ENTRYTEST", e.toString());
        }

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            Log.d("ENTRYTEST", e.toString());
        }

        //Get the count after adding one
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                                     @Override
                                                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                         //dataSnapshot.child("WHAT GOES HERE?");
                                                         countAfter = dataSnapshot.child("uploads").getChildrenCount();
                                                         Log.d("ENTRYTEST", "count after: " + countAfter);
                                                     }

                                                     @Override
                                                     public void onCancelled(@NonNull DatabaseError databaseError) {
                                                         Log.e("ENTRYTEST", "onCancelled ran");
                                                     }
                                                 } );
        //another wait..
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            Log.d("ENTRYTEST", e.toString());
        }

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            Log.d("ENTRYTEST", e.toString());
        }

        Log.d("ENTRYTEST", "final counts. BEFORE: " + countBefore + " AFTER: " + countAfter);



        //Check if one entry was added
        assertEquals(countBefore+1, countAfter);

        //should delete the entry after the test






    }

}