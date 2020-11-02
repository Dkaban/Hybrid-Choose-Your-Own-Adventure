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
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ChooseAdventureActivity extends AppCompatActivity
{
    ArrayList<Dialogue> dialogueList = new ArrayList<>();
    TextView mainDialogueStatementText;
    Button optionOneButton;
    Button optionTwoButton;
    //Button optionThreeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_adventure);

        mainDialogueStatementText = this.findViewById(R.id.text_adventureDialogue);
        optionOneButton = this.findViewById(R.id.button_option1);
        optionTwoButton = this.findViewById(R.id.button_option2);
        //optionThreeButton = this.findViewById(R.id.button_option3);

        //TODO: MOVE LOADING TO ON LAUNCH VS IN THE GAMEPLAY FILE
        //Need to initialize all the Dialogue in an array so we can access it.
        populateDialogueArray();
        //Initialize the dialogue options, so the tree knows where to go
        setDialogueOptionIdexes();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        //Initially setting it to be 0 because that will be our default starting dialogue state
        setUIDialogue(DataHolder.getInstance().getCurrentDialogueIndex());
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

    public void populateDialogueArray()
    {
        try {
            loadDialogueFromTextFile();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private void loadDialogueFromTextFile() throws IOException
    {
        ArrayList<String> tempTextList = new ArrayList<>();
        InputStream inputStream =
                getApplicationContext().getAssets().open("dialogueFile.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        while((line = reader.readLine()) != null)
        {
            tempTextList.add(line);
        }

        sortDialogueTypesIntoDialogueArray(tempTextList);
    }

    private void sortDialogueTypesIntoDialogueArray(ArrayList<String> tempTextList)
    {
        Dialogue dialogue = new Dialogue();
        for(int i=0;i<tempTextList.size();i++)
        {
            if(i%3 == 0)
            {
                dialogue = new Dialogue();
                dialogue.setDialogueStatement(tempTextList.get(i));
                dialogueList.add(dialogue);
            }
            else
            {
                dialogue.addOption(tempTextList.get(i));
            }
        }
    }

    private void setUIDialogue(int dialogueIndex)
    {
        System.out.println("UI DIALOGUE: " + dialogueIndex);
        if(dialogueIndex == 1000)
        {
            //load the first game activity
            loadActivity(GameOneActivity.class);
        }
        else if(dialogueIndex == 2000)
        {
            //load the second game activity
            loadActivity(GameTwoActivity.class);
        }
        else if(dialogueIndex == 3000)
        {
            //load the third game activity
            loadActivity(GameThreeActivity.class);
        }
        else if(dialogueIndex == 998)
        {
            //load the leaderBoards activity
            loadActivity(LeaderBoardActivity.class);
        }
        else
        {
            Dialogue dialogue = dialogueList.get(dialogueIndex);
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
            index = dialogueList.get(DataHolder.getInstance().getCurrentDialogueIndex()).getIndexForOption1();
            System.out.println("Button 1 Pressed Index " + index + " Found.");
        }
        else
        {
            //Get the new dialogueIndex
            index = dialogueList.get(DataHolder.getInstance().getCurrentDialogueIndex()).getIndexForOption2();
            System.out.println("Button 2 Pressed Index " + index + " Found.");
        }

        //Set the UI dialogue to match the new index we're moving too in the dialogue tree
        setUIDialogue(index);
    }

    private void setDialogueOptionIdexes()
    {
        //CODES: 1000 = Play Game 1, 999 = Restart Game, 998 = LeaderBoards
        //TODO: Create better ways to detecting game end or changing games
        dialogueList.get(0).setOptionIndexes(1,2);//Go to Tavern or Go to Inn
        dialogueList.get(1).setOptionIndexes(3,5);//Approach Bartender or Approach Piano Player
        dialogueList.get(2).setOptionIndexes(6,7);//Talk to guests or talk to innkeeper
        dialogueList.get(3).setOptionIndexes(1000,4); //Play a game or DIE
        dialogueList.get(4).setOptionIndexes(0,998); //Restart or Go to LeaderBoards
        dialogueList.get(5).setOptionIndexes(1000,1);//Play game or Go To Tavern
        dialogueList.get(6).setOptionIndexes(9,8);//Chicken or Beef
        dialogueList.get(7).setOptionIndexes(10,0);//Would you like a room, yes, no
        dialogueList.get(8).setOptionIndexes(1000,9);//Play a game or Chicken
        dialogueList.get(9).setOptionIndexes(0,998);//Restart or Go to LeaderBoards
        dialogueList.get(10).setOptionIndexes(1000,4);//Play Game or get a drink from stranger
        dialogueList.get(11).setOptionIndexes(4,12); //DIE by Poison or go to sleep and go outside
        dialogueList.get(12).setOptionIndexes(1,2); //Go to tavern or Go to Inn
        dialogueList.get(13).setOptionIndexes(0,998); //You died in the duel, restart or leaderBoards
        dialogueList.get(14).setOptionIndexes(15,16); //Saddle up or Saunter into town
        dialogueList.get(15).setOptionIndexes(18,17); //Go towards noise or yell Hi-yo Silver
        dialogueList.get(16).setOptionIndexes(15,18); //Saddle up or yell hi-yo silver
        dialogueList.get(17).setOptionIndexes(15,18); //Saddle up or run towards noise
        dialogueList.get(18).setOptionIndexes(19,20); //Reach the noise: Start shooting, run for cover
        dialogueList.get(19).setOptionIndexes(0,998); //Start Shooting: Restart Game, go to leaderBoards
        dialogueList.get(20).setOptionIndexes(21,22); //Cover: Start Shooting or tell old man to run
        dialogueList.get(21).setOptionIndexes(24,23); //Kill Bandits: Help or Scout
        dialogueList.get(22).setOptionIndexes(20,25); //Yell at old man: Start Shooting, tackle old man
        dialogueList.get(23).setOptionIndexes(26,25); //Scout: Talk to or tackle old man
        dialogueList.get(24).setOptionIndexes(0,998); //Lose: Restart or LeaderBoards
        dialogueList.get(25).setOptionIndexes(27,27); //Tackle: Grandpa or Do I Know you
        dialogueList.get(26).setOptionIndexes(27,27); //Talk: Grandpa or Do I Know you
        dialogueList.get(27).setOptionIndexes(2000,3000); //Math problem or Word Problem
        System.out.println("Dialogue List Size: " + dialogueList.size());
    }

    //Loads the activity via intent
    private void loadActivity(Class activity)
    {
        Intent activityToStart = new Intent(this,activity);
        startActivity(activityToStart);
    }
}

class Dialogue
{
    private String dialogueStatement;
    private ArrayList<String> optionsList = new ArrayList<>();
    private int indexForOption1;
    private int indexForOption2;

    public void setDialogueStatement(String text)
    {
        dialogueStatement = text;
    }

    public String getDialogueStatement()
    {
        return dialogueStatement;
    }

    public ArrayList<String> getOptions()
    {
        return optionsList;
    }

    public void addOption(String option)
    {
        optionsList.add(option);
    }

    public void setOptionIndexes(int index1, int index2)
    {
        indexForOption1 = index1;
        indexForOption2 = index2;
    }

    public int getIndexForOption1()
    {
        return indexForOption1;
    }

    public int getIndexForOption2()
    {
        return indexForOption2;
    }
}