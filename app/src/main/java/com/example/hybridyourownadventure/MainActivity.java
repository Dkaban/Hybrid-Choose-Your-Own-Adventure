/**
 * Author: Dustin Kaban
 * StudentID: T00663749
 *
 * Class: MainActivity.java
 *
 * This class displays the main menu to the user and loads the dialogue from a text file
 * It also plays the music that loops
 *
 */

package com.example.hybridyourownadventure;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    private SharedPreferences sharedPreferences;
    private String PREF_NAME = "FirstPlay";
    private SharedPreferences.Editor editor;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(PREF_NAME,0);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        //We check to see if the list is empty, we only want to populate it once
        if(DataHolder.getInstance().dialogueList.isEmpty())
        {
            //Populate the dialogue array via a text file
            //Assign the dialogue tree values so we know what dialogue takes you where
            DataHolder.getInstance().loadDialogueData(this);
        }

        //If it's the first time we're playing, we want to display the copyright / disclaimer page
        boolean firstPlay= false;
        if(!sharedPreferences.getBoolean(PREF_NAME,firstPlay))
        {
            DataHolder.getInstance().loadHandler.loadActivity(this,SplashActivity.class);
        }
    }

    //This method checks to see what button is clicked and changes to the corresponding activity
    public void onClickMenuOption(View view)
    {
        switch(view.getId())
        {
            case R.id.button_playGame:
                DataHolder.getInstance().loadHandler.loadActivity(this,ChooseAdventureActivity.class);
                break;

            case R.id.button_options:
                DataHolder.getInstance().loadHandler.loadActivity(this,OptionsActivity.class);
                break;

            case R.id.button_leaderboards:
                DataHolder.getInstance().loadHandler.loadActivity(this,LeaderBoardActivity.class);
                break;
        }
    }
}