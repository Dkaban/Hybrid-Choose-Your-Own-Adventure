/**
 * Author: Dustin Kaban
 * StudentID: T00663749
 *
 * Class: OptionsActivity.java
 *
 * //TODO: WRITE A DESCRIPTION FOR THIS CLASS
 */

package com.example.hybridyourownadventure;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity
{
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    public void onClickMuteAudio(View view)
    {
        //Mute the Audio (Game Music)
        stopAudioService();

        if(DataHolder.getInstance().getMuteAudio())
        {
            Toast.makeText(this, "Audio On", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Audio Off", Toast.LENGTH_SHORT).show();
        }

        DataHolder.getInstance().setMuteAudio();
    }

    public void onClickClearSavedData(View view)
    {
        clearData();
        Toast.makeText(this, "All Data Cleared", Toast.LENGTH_SHORT).show();
    }

    private void clearData()
    {
        //We want to clear the sessions,but we don't want to clear the FIRST PLAY data.
        //Because at this point we know the user has played more than once, so no need to show again
        String Pref_sessionsPlayed = "SessionsPlayed";
        sharedPreferences = getSharedPreferences(Pref_sessionsPlayed,0);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public void stopAudioService()
    {
        stopService(new Intent(this,AudioService.class));
    }

    public void onClickMainMenu(View view)
    {
        loadActivity(MainActivity.class);
    }

    public void onClickLeaderBoards(View view)
    {
        loadActivity(LeaderBoardActivity.class);
    }

    //Loads the activity via intent
    private void loadActivity(Class activity)
    {
        Intent activityToStart = new Intent(this,activity);
        startActivity(activityToStart);
    }
}