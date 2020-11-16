/**
 * Author: Dustin Kaban
 * StudentID: T00663749
 *
 * Class: DataHolder.java
 *
 * This class is for maintaining a persistent gameplay session
 * Also saving if the user goes back and returns to the game Activity
 */

package com.example.hybridyourownadventure;
import android.content.Context;
import java.util.ArrayList;

public class DataHolder
{
    //Holds all of the Dialogue inputted from a text file
    public ArrayList<Dialogue> dialogueList = new ArrayList<>();
    private boolean gameOver = false;
    //This will be used to track which dialogue we've left off on
    private int currentDialogueIndex = 0;
    private boolean audioMuted = false;
    public LoadHandler loadHandler = new LoadHandler();

    public void loadDialogueData(Context context)
    {
        dialogueList = loadHandler.loadDialogueData(context);
    }

    public void setCurrentDialogueIndex(int index)
    {
        currentDialogueIndex = index;
    }

    public int getCurrentDialogueIndex()
    {
        return currentDialogueIndex;
    }

    public void setGameOver(boolean gameOverState)
    {
        gameOver = gameOverState;
    }

    public boolean getGameOver()
    {
        return gameOver;
    }

    public void setMuteAudio()
    {
        if(audioMuted)
        {
            //Un-mute the audio if it is muted
            audioMuted = false;
        }
        else
        {
            //Mute the audio if it isn't muted
            audioMuted = true;
        }
    }

    public boolean getMuteAudio()
    {
        return audioMuted;
    }

    //Allows for use-ability across activity changes
    static DataHolder getInstance()
    {
        if( instance == null )
        {
            instance = new DataHolder();
        }
        return instance;
    }

    private static DataHolder instance;
}
