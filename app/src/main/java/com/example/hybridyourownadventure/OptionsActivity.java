package com.example.hybridyourownadventure;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OptionsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    public void onClickMuteAudio(View view)
    {
        //TODO: Mute all Audio
    }

    public void onClickClearSavedData(View view)
    {
        //TODO: Clear all the SharedPreference data
    }
}