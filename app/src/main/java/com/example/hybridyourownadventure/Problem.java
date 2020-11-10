/**
 * Author: Dustin Kaban
 * StudentID: T00663749
 *
 * Class: Problem.java
 *
 * This class handles all of the problem information for GameTwo (Math Problem) and
 * Game Three (Word Problem)
 *
 */

package com.example.hybridyourownadventure;
public class Problem
{
    private String question;
    private String answer;

    public Problem(String q, String a)
    {
        question = q;
        answer = a;
    }

    public String getQuestion()
    {
        return question;
    }

    public String getWordAnswer()
    {
        return answer;
    }

    public int getMathAnswer()
    {
        return Integer.parseInt(answer);
    }
}
