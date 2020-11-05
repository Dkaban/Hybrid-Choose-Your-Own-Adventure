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
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        //We check to see if the list is empty, we only want to populate it once
        if(DataHolder.getInstance().dialogueList.isEmpty())
        {
            //Populate the dialogue array via a text file
            DataHolder.getInstance().populateDialogueArray(this);
            //Assign the dialogue tree values so we know what dialogue takes you where
            DataHolder.getInstance().setDialogueOptionIndexes();
        }
    }

    //This method checks to see what button is clicked and changes to the corresponding activity
    public void onClickMenuOption(View view)
    {
        switch(view.getId())
        {
            case R.id.button_playGame:
                loadActivity(ChooseAdventureActivity.class);
                break;

            case R.id.button_options:
                loadActivity(OptionsActivity.class);
                break;

            case R.id.button_leaderboards:
                loadActivity(LeaderBoardActivity.class);
                break;
        }
    }

    //Loads the activity via intent
    private void loadActivity(Class activity)
    {
        Intent activityToStart = new Intent(this,activity);
        startActivity(activityToStart);
    }
}