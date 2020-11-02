/**
 * Author: Dustin Kaban
 * StudentID: T00663749
 *
 * Class: GameOneActivity.java
 *
 * This game is a simple version of a "duel" system in the wild west.
 * The user is prompted to push a button at a random interval within X amount of seconds
 * If the user presses the button, they win the duel. If not, they lose.
 */

package com.example.hybridyourownadventure;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameOneActivity extends AppCompatActivity
{
    private boolean timeToDraw = true;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);
    }

    public void onClickDraw(View view)
    {
        if(timeToDraw)
        {
            //WIN!!
            //Move on to the next dialogue piece
            DataHolder.getInstance().setCurrentDialogueIndex(14);
        }
        else
        {
            //LOSE!!
            //TODO: UPDATE THE SHAREDPREFERENCES WITH STATS HERE
            DataHolder.getInstance().setCurrentDialogueIndex(13);
        }

        loadActivity(ChooseAdventureActivity.class);
    }

    //Loads the activity via intent
    private void loadActivity(Class activity)
    {
        Intent activityToStart = new Intent(this,activity);
        startActivity(activityToStart);
    }
}