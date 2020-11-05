/**
 * Author: Dustin Kaban
 * StudentID: T00663749
 *
 * Class: AudioService.java
 *
 * This service class handles all of the audio in the game
 * Currently only handling music playing
 *
 */
package com.example.hybridyourownadventure;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class AudioService extends Service
{
    MediaPlayer mpAudio;

    public AudioService()
    {

    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        mpAudio = MediaPlayer.create(this,R.raw.Song_01);
        mpAudio.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}