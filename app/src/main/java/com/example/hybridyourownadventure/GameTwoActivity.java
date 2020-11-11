/**
 * Author: Dustin Kaban
 * StudentID: T00663749
 *
 * Class: GameTwoActivity.java
 *
 * This class handles the Solve a Math Problem question, picks the random question
 * Then displays it to the user
 * The user is allowed 3 guesses until they fail
 */

package com.example.hybridyourownadventure;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameTwoActivity extends AppCompatActivity
{
    private Problem[] mathProblems;
    private int chosenIndex;
    private int chancesLeft = 3;

    private TextView questionTextView;
    private EditText answerEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_two);
        questionTextView = findViewById(R.id.text_questionGameTwo);
        answerEditText = findViewById(R.id.editText_gameTwoAnswerEntry);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        populateMathProblemsArray();
        chosenIndex = pickRandomQuestion();
        questionTextView.setText(mathProblems[chosenIndex].getQuestion());
    }

    public void onClickAnswer(View view)
    {
        //Used to track if we've found the correct answer or not, so the finally doesn't trigger
        boolean foundCorrectAnswer = false;
        try
        {
            //Returns a boolean to see if we've found the correct answer
            foundCorrectAnswer = checkForCorrectAnswer();
            //We want to load the EndGameActivity class since the user got the answer correct
            loadActivity(EndGameActivity.class);
        }
        catch (Exception e)
        {
            //Failed to parse integer, make sure to catch exception
        }
        finally
        {
            //We want to reduce their number of chances or end the game regardless
            if(!foundCorrectAnswer)
            {
                checkForGameOver();
            }
        }
    }

    private boolean checkForCorrectAnswer()
    {
        if(mathProblems[chosenIndex].getMathAnswer() == Integer.parseInt(answerEditText.getText().toString()))
        {
            return true;
        }
        return false;
    }

    private void checkForGameOver()
    {
        if(chancesLeft > 0)
        {
            //Failed to parse the integer, we want to remove a chance
            chancesLeft--;
            Toast.makeText(this, "Incorrect! You have " + chancesLeft + " chance(s) to get the answer correct before game over.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //Set GameOver to true so we know the player lost the game in the EndGameActivity
            DataHolder.getInstance().setGameOver(true);
            //GAME OVER! FAIL
            loadActivity(EndGameActivity.class);
        }
    }

    @Override
    public void onBackPressed()
    {
        //super.onBackPressed();
        //We want to do nothing here, disabling it helps stop cheating!
    }

    private void populateMathProblemsArray()
    {
        mathProblems = new Problem[3];
        //Doing this manually, we could load a text file to populate this list too.
        Problem mp = new Problem("What is 1 + 1?","2");
        mathProblems[0] = mp;
        mp = new Problem("2 + 2","4");
        mathProblems[1] = mp;
        mp = new Problem("3 + 3","6");
        mathProblems[2] = mp;
    }

    private int pickRandomQuestion()
    {
        //Returns a random number between 0 and the mathProblems array size
        return new Random().nextInt(mathProblems.length-1);
    }

    //Loads the activity via intent
    private void loadActivity(Class activity)
    {
        Intent activityToStart = new Intent(this,activity);
        startActivity(activityToStart);
    }
}