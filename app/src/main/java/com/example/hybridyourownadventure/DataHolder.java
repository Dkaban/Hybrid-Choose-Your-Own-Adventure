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

public class DataHolder
{
    private boolean gameOver = false;
    //This will be used to track which dialogue we've left off on
    private int currentDialogueIndex = 0;

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
