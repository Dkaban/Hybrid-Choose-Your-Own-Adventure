/**
 * Author: Dustin Kaban
 * StudentID: T00663749
 *
 * Class: MainActivity.java
 *
 * //TODO: WRITE A DESCRIPTION FOR THIS CLASS
 */

package com.example.hybridyourownadventure;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
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