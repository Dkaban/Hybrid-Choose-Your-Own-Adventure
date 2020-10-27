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
    Button optionThreeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_adventure);

        mainDialogueStatementText = this.findViewById(R.id.text_adventureDialogue);
        optionOneButton = this.findViewById(R.id.button_option1);
        optionTwoButton = this.findViewById(R.id.button_option2);
        optionThreeButton = this.findViewById(R.id.button_option3);

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
                System.out.println("Pressed Option 1");
                break;

            case R.id.button_option2:
                System.out.println("Pressed Option 2");
                break;

            case R.id.button_option3:
                System.out.println("Pressed Option 3");
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
        InputStream inputStream = getApplicationContext().getAssets().open("dialogueFile.txt");
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
            if(i%4 == 0)
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

        for(int i=0;i<dialogueList.size();i++)
        {
            System.out.println("DIAG Statement: " + dialogueList.get(i).getDialogueStatement());
            for(int k=0;k<dialogueList.get(i).getOptions().size();k++)
            {
                System.out.println("DIAG Option: " + dialogueList.get(i).getOptions().get(k));
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
        optionThreeButton.setText(dialogue.getOptions().get(2));
    }
}

class Dialogue
{
    private String dialogueStatement;
    private ArrayList<String> optionsList = new ArrayList<>();

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
}