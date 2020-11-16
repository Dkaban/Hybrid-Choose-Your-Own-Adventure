/**
 * Author: Dustin Kaban
 * StudentID: T00663749
 *
 * Class: SplashActivity.java
 *
 * This Activity handles displaying the copyright and other starting information to the user
 * This is only displayed once and on startup
 */

package com.example.hybridyourownadventure;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity
{
    private SharedPreferences sharedPreferences;
    private String PREF_NAME = "FirstPlay";
    private SharedPreferences.Editor editor;
    private int totalSeconds = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferences = getSharedPreferences(PREF_NAME,0);
        editor = sharedPreferences.edit();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        //We want to save the fact the player has no played the game > 0 times
        setFirstPlay();
        //Run the timer so we can close the screen after X amount of seconds
        runTimer();
    }

    private void setFirstPlay()
    {
        editor.putBoolean(PREF_NAME,true);
        editor.commit();
    }

    private void runTimer()
    {
        //Set the timer to go to a max of 25 seconds
        new CountDownTimer(totalSeconds, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                //Do nothing
            }

            public void onFinish()
            {
                //Go back to the MainActivity
                loadActivity(MainActivity.class);
            }

        }.start();
    }

    //Loads the activity via intent
    private void loadActivity(Class activity)
    {
        DataHolder.getInstance().loadHandler.loadActivity(this,activity);
    }
}