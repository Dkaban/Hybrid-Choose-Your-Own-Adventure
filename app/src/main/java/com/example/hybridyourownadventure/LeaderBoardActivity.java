/**
 * Author: Dustin Kaban
 * StudentID: T00663749
 *
 * Class: LeaderBoardActivity.java
 *
 * This class displays the users stats to the user.
 * It reads from SharedPreferences and outputs the data, it does not edit anything
 */

package com.example.hybridyourownadventure;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class LeaderBoardActivity extends AppCompatActivity
{
    private String PREF_SESSIONSPLAYED = "SessionsPlayed";
    private SharedPreferences sharedPreferences;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        sharedPreferences = getSharedPreferences(PREF_SESSIONSPLAYED,0);
        listView = this.findViewById(R.id.listView_playerStats);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        ArrayList<String> tempStringArray = new ArrayList<>();

        //TODO: Add more Stats here
        tempStringArray.add("Sessions Played: " + sharedPreferences.getInt(PREF_SESSIONSPLAYED,0));

        //Display the stats, we need an adapter to do that
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tempStringArray);
        listView.setAdapter(adapter);
    }
}