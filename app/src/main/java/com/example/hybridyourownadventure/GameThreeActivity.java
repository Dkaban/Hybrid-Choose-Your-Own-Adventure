/**
 * Author: Dustin Kaban
 * StudentID: T00663749
 *
 * Class: GameThreeActivity.java
 *
 * The user is prompted with a word problem and they are given 3 chances to submit
 * the correct answer
 */

package com.example.hybridyourownadventure;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameThreeActivity extends AppCompatActivity
{
    private Problem[] wordProblems;
    private int chosenIndex;
    private int chancesLeft = 3;

    private TextView questionTextView;
    private EditText answerEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_three);
        questionTextView = findViewById(R.id.text_questionGameThree);
        answerEditText = findViewById(R.id.editText_gameThreeAnswerEntry);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Toast.makeText(this, "All answers are one word only.", Toast.LENGTH_SHORT).show();
        populateWordProblemsArray();
        chosenIndex = pickRandomQuestion();
        questionTextView.setText(wordProblems[chosenIndex].getQuestion());
    }

    public void onClickAnswer(View view)
    {
        //Submit answer
        //When the user presses the Check Answer button
        //If the answer inputted is correct, they've won the game!!
        if(wordProblems[chosenIndex].getWordAnswer().toLowerCase().equals(answerEditText.getText().toString().toLowerCase()))
        {
            DataHolder.getInstance().loadHandler.loadActivity(this,EndGameActivity.class);
        }
        else if(chancesLeft > 0)
        {
            chancesLeft--;
            Toast.makeText(this, "Incorrect! You have " + chancesLeft + " chance(s) to get the answer correct before game over.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //Set GameOver to true so we know the player lost the game in the EndGameActivity
            DataHolder.getInstance().setGameOver(true);
            //GAME OVER! FAIL
            DataHolder.getInstance().loadHandler.loadActivity(this,EndGameActivity.class);
        }
    }

    @Override
    public void onBackPressed()
    {
        //super.onBackPressed();
        //We want to do nothing here, disabling it helps stop cheating!
    }

    private void populateWordProblemsArray()
    {
        wordProblems = new Problem[3];
        //Doing this manually, we could load a text file to populate this list too.
        Problem mp = new Problem("Unscramble the letters bycwoo","cowboy");
        wordProblems[0] = mp;
        mp = new Problem("What can you catch but never throw?","cold");
        wordProblems[1] = mp;
        mp = new Problem("What runs around the whole yard without moving?","fence");
        wordProblems[2] = mp;
    }

    private int pickRandomQuestion()
    {
        return new Random().nextInt(wordProblems.length-1);
    }
}