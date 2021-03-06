/**
 * Author: Dustin Kaban
 * StudentID: T00663749
 *
 * Class: LoadHandler.java
 *
 * This class handles all the loading we require.
 * Takes the pressure off of DataHolder that is already doing some heavy lifting.
 *
 */

package com.example.hybridyourownadventure;
import android.content.Context;
import android.content.Intent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoadHandler
{
    //Holds all of the Dialogue inputted from a text file
    private ArrayList<Dialogue> dialogueList = new ArrayList<>();

    public ArrayList<Dialogue> loadDialogueData(Context context)
    {
        populateDialogueArray(context);
        setDialogueOptionIndexes();
        return dialogueList;
    }

    private void populateDialogueArray(Context context)
    {
        try {
            loadDialogueFromTextFile(context);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private void loadDialogueFromTextFile(Context context) throws IOException
    {
        ArrayList<String> tempTextList = new ArrayList<>();
        InputStream inputStream =
                context.getAssets().open("dialogueFile.txt");
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

    private void setDialogueOptionIndexes()
    {
        //This is how we know which Dialogue goes to which Dialogue essentially the Tree logic.
        //CODES: 1000 = Play Game 1, 999 = Restart Game, 998 = LeaderBoards
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
    }

    //Loads the activity via intent
    public void loadActivity(Context context, Class activity)
    {
        Intent activityToStart = new Intent(context,activity);
        context.startActivity(activityToStart);
    }
}
