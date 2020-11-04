/**
 * Author: Dustin Kaban
 * StudentID: T00663749
 *
 * Class: Dialogue.java
 *
 * This dialogue object holds everything to do with the dialogue.
 * We need this class because it helps keep track of where the branching dialogue goes
 * and also holds the dialogue itself so we can display it to the user
 */

package com.example.hybridyourownadventure;
import java.util.ArrayList;

class Dialogue
{
    ArrayList<Dialogue> dialogueList = new ArrayList<>();

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
