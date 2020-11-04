/**
 * Author: Dustin Kaban
 * StudentID: T00663749
 *
 * Class: GameTwoActivity.java
 *
 * //TODO: WRITE DESCRIPTION FOR THIS MATH PROBLEM
 */

package com.example.hybridyourownadventure;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GameTwoActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_two);
    }

    @Override
    public void onBackPressed()
    {
        //super.onBackPressed();
        //We want to do nothing here, disabling it helps stop cheating!
    }
}