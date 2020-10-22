package com.example.hybridyourownadventure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseAdventureActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_adventure);
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
}