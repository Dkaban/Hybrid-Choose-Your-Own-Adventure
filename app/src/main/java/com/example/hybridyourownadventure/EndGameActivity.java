/**
 * Author: Dustin Kaban
 * StudentID: T00663749
 *
 * Class: EndGameActivity.java
 *
 * This activity handles when the game is over, either by win or loss
 * Displays a screen to the user and sends them back to the very first screen
 * This activity only displays if the User is on the Game and Wins / Loses
 * There are intermediate game over screens, but this is the Final end game screen
 */

package com.example.hybridyourownadventure;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndGameActivity extends AppCompatActivity
{
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        textView = findViewById(R.id.text_endGame);
        button = findViewById(R.id.button_endGame);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if(DataHolder.getInstance().getGameOver())
        {
            //The player lost the game
            textView.setText("Game Over.  The wild west is a tough place.  Stay tuned for more adventures!");
            button.setText("Try Again");
        }
        else
        {
            //The player won the game
            //The player lost the game
            textView.setText("Congratulations you have won the game!  You are the best.  Stay tuned for more adventures!");
            button.setText("Accept Success");
        }

        //We want to set the default values for a fresh game
        DataHolder.getInstance().setCurrentDialogueIndex(0);
        DataHolder.getInstance().setGameOver(false);
    }

    public void onClickEndGame(View view)
    {
        //We want to set the default values for a fresh game
        DataHolder.getInstance().setCurrentDialogueIndex(0);
        DataHolder.getInstance().setGameOver(false);

        //Load the game back to the beginning again
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
        //Do nothing, we don't want the player to be able to go back.
        //super.onBackPressed();
    }
}