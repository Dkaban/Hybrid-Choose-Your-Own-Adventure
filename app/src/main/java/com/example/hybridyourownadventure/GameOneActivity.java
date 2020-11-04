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
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class GameOneActivity extends AppCompatActivity
{
    private boolean validDraw = false;
    private boolean timeToDraw = false;
    private Button drawButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);

        drawButton = this.findViewById(R.id.button_drawButton);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        draw();
    }

    private void draw()
    {
        //Pick a random number from 6 to 15 seconds
        Random random = new Random();
        int randomInterval = random.nextInt(20 - 15 + 1) + 15;

        //Set the timer to go to a max of 25 seconds
        new CountDownTimer(25000, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                //If we've passed the random number, we want to allow the user to draw their weapon
                if((millisUntilFinished/1000) <= randomInterval && !timeToDraw)
                {
                    drawButton.setText("DRAW");
                    timeToDraw = true;
                }
                else if((millisUntilFinished/1000) <= (randomInterval-3))
                {
                    //If we've exceeded 3 seconds past the random interval, the draw is failed
                    drawButton.setText("Too Late\n(tap here to continue story)");
                    timeToDraw = false;
                    cancel();
                }
            }

            public void onFinish()
            {
                //mTextField.setText("done!");
            }

        }.start();
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

    @Override
    public void onBackPressed()
    {
        //super.onBackPressed();
        //We want to do nothing here, disabling it helps stop cheating!
    }
}