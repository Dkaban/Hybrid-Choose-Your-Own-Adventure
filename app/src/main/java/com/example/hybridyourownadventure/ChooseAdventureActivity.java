/**
 * Author: Dustin Kaban
 * StudentID: T00663749
 *
 * Class: ChooseAdventureActivity.java
 *
 * //TODO: WRITE A DESCRIPTION FOR THIS CLASS
 * Handles the main game play functionality.
 */

package com.example.hybridyourownadventure;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class ChooseAdventureActivity extends AppCompatActivity
{
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    ArrayList<Dialogue> dialogueList = new ArrayList<>();
    TextView mainDialogueStatementText;
    Button optionOneButton;
    Button optionTwoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_adventure);

        mainDialogueStatementText = this.findViewById(R.id.text_adventureDialogue);
        optionOneButton = this.findViewById(R.id.button_option1);
        optionTwoButton = this.findViewById(R.id.button_option2);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        //Initially setting it to be 0 because that will be our default starting dialogue state
        setUIDialogue(DataHolder.getInstance().getCurrentDialogueIndex());

        //Start playing the Audio, if the user has not muted it via the Options
        if(!DataHolder.getInstance().getMuteAudio())
        {
            startAudioService();
        }
    }

    public void onClickAdventureOption(View view)
    {
        Button b;
        switch(view.getId())
        {
            case R.id.button_option1:
                goToNextDialogue(1);
                break;

            case R.id.button_option2:
                goToNextDialogue(2);
                break;

            default:
                break;
        }
    }

    private void setUIDialogue(int dialogueIndex)
    {
        System.out.println("UI DIALOGUE: " + dialogueIndex);
        if(dialogueIndex == 1000)
        {
            //load the first game activity
            DataHolder.getInstance().loadHandler.loadActivity(this,GameOneActivity.class);
        }
        else if(dialogueIndex == 2000)
        {
            //load the second game activity
            DataHolder.getInstance().loadHandler.loadActivity(this,GameTwoActivity.class);
        }
        else if(dialogueIndex == 3000)
        {
            //load the third game activity
            DataHolder.getInstance().loadHandler.loadActivity(this,GameThreeActivity.class);
        }
        else if(dialogueIndex == 998)
        {
            //load the leaderBoards activity
            DataHolder.getInstance().loadHandler.loadActivity(this,LeaderBoardActivity.class);
        }
        else
        {
            Dialogue dialogue = DataHolder.getInstance().dialogueList.get(dialogueIndex);
            mainDialogueStatementText.setText(dialogue.getDialogueStatement());
            optionOneButton.setText(dialogue.getOptions().get(0));
            optionTwoButton.setText(dialogue.getOptions().get(1));
            DataHolder.getInstance().setCurrentDialogueIndex(dialogueIndex);
        }
    }

    private void goToNextDialogue(int buttonPressed)
    {
        int index = 0;
        //We want to set the current dialogue index according to the preset dialogue path
        //We set up in the initialization
        if(buttonPressed == 1)
        {
            //Get the new dialogue index
            index = DataHolder.getInstance().dialogueList.get(
                    DataHolder.getInstance().getCurrentDialogueIndex()).getIndexForOption1();
        }
        else
        {
            //Get the new dialogueIndex
            index = DataHolder.getInstance().dialogueList.get(
                    DataHolder.getInstance().getCurrentDialogueIndex()).getIndexForOption2();
        }

        //If the player goes back to the main dialogue, they've lost or restarted
        if(index == 0)
        {
            saveStats();
        }

        //Set the UI dialogue to match the new index we're moving too in the dialogue tree
        setUIDialogue(index);
    }

    private void saveStats()
    {
        String Pref_sessionsPlayed = "SessionsPlayed";
        sharedPreferences = getSharedPreferences(Pref_sessionsPlayed,0);
        editor = sharedPreferences.edit();

        int sessionsPlayed = sharedPreferences.getInt(Pref_sessionsPlayed,0);
        sessionsPlayed++;
        editor.putInt(Pref_sessionsPlayed,sessionsPlayed);
        editor.apply();
    }

    //We want to pause the music when the user closes the app or leaves the game scene
    @Override
    protected void onPause()
    {
        super.onPause();
        stopAudioService();
    }

    public void startAudioService()
    {
        startService(new Intent(this,AudioService.class));
    }

    public void stopAudioService()
    {
        stopService(new Intent(this,AudioService.class));
    }

    public void onClickMainMenu(View view)
    {
        DataHolder.getInstance().loadHandler.loadActivity(this,MainActivity.class);
    }

    public void onClickOptions(View view)
    {
        DataHolder.getInstance().loadHandler.loadActivity(this,OptionsActivity.class);
    }
}