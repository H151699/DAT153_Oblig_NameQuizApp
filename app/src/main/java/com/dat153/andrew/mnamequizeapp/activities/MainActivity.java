package com.dat153.andrew.mnamequizeapp.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.dat153.andrew.mnamequizeapp.R;

public class MainActivity extends AppCompatActivity {

    /** Duration of waiting  3.5s  **/
    final int SPLASH_DISPLAY_LENGTH = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);




        /** new Handler to start the WhoIsWhoActivity(Quiz Acitivity), last 3 sec**/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this,WhoIsWhoActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();

            }

        }, SPLASH_DISPLAY_LENGTH);
    }


    /** Open pop up alert dialog **/
    public void openDialog(){


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}