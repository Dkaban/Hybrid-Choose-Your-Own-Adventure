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
        //Initially setting it to be 0 because that will be our default starting dialogue state
        setUIDialogue(0);
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
        //TODO: Populate the UI given the corresponding Dialogue
        //TODO: ADD ACTUAL LOGIC BEHIND HOW TEXT IS CHOSEN
        Dialogue dialogue = dialogueList.get(dialogueIndex);
        mainDialogueStatementText.setText(dialogue.getDialogueStatement());
        optionOneButton.setText(dialogue.getOptions().get(0));
        optionTwoButton.setText(dialogue.getOptions().get(1));
    }

    private void goToNextDialogue(int buttonPressed)
    {
        if(buttonPressed == 1)
        {

        }
        else
        {

        }
    }

    private void setDialogueOptionIdexes()
    {
        //CODES: 1000 = Play Game 1, 999 = Restart Game, 998 = LeaderBoards
        //TODO: Create better ways to detecting game end or changing games
        dialogueList.get(0).setOptionIndexes(1,2);//Go to Tavern or Go to Inn
        dialogueList.get(1).setOptionIndexes(3,5);//Approach Bartender or Approach Piano Player
        dialogueList.get(2).setOptionIndexes(7,6);//Talk to Innkeeper or Talk to Guests
        dialogueList.get(3).setOptionIndexes(4,1000); //DIE or Play a Game (1000 being GAME1)
        dialogueList.get(4).setOptionIndexes(999,998); //Restart or Go to LeaderBoards
        dialogueList.get(5).setOptionIndexes(1000,1);//Play game or Go To Tavern
        dialogueList.get(6).setOptionIndexes(9,8);//Chicken or Beef
        dialogueList.get(7).setOptionIndexes(1000,0);//Play game or No, restart
        dialogueList.get(8).setOptionIndexes(9,1000);//Chicken or Play Game
        dialogueList.get(9).setOptionIndexes(999,998);//Restart or Go to LeaderBoards
        dialogueList.get(10).setOptionIndexes(999,998);//Restart or Go to LeaderBoards
        //dialogueList.get(11).setOptionIndexes(999,998); // WIN A GAME, NOT SURE YET
    }
}

class Dialogue
{
    private String dialogueStatement;
    private ArrayList<String> optionsList = new ArrayList<>();
    private int indexForOption1 = 0;
    private int indexForOption2 = 1;

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
        indexForOption1 = index2;
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