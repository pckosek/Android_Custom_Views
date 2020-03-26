package com.example.pckosek.soundcallback_02;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity implements MySoundView.SoundViewListener {

    MySoundView mySoundView;

    private SoundPool mSoundPool;
    float actVolume, maxVolume, volume;
    AudioManager audioManager;

    int mLowCount  = 0;
    int mHighCount = 0;


    int[][] mSoundResources = new int[4][9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySoundView = findViewById(R.id.sound_view);

        // AudioManager audio settings for adjusting the volume
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;

        //Hardware buttons setting to adjust the media sound
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);


        // Load the sounds
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();

        mSoundPool = new SoundPool.Builder()
                .setMaxStreams(20)
                .setAudioAttributes(audioAttributes)
                .build();

        setupRawAudio();
    }

    public void setupRawAudio(){
        int c_indx = 0;
        int g_indx = 0;
        int a_indx = 0;
        int f_indx = 0;

        int resource;

        //  GET FIELDS
        Field[] fields=R.raw.class.getFields();

        for (Field f_name : fields ) {
            String s = f_name.getName();
            String[] tokens = s.split("_");

            switch (tokens[0]) {
                case "cmaj" :
                    resource = getResources().getIdentifier(s,
                            "raw", getPackageName());
                    mSoundResources[0][c_indx++] = mSoundPool.load(this, resource, 1);
                    break;
                case "gmaj" :
                    resource = getResources().getIdentifier(s,
                            "raw", getPackageName());
                    mSoundResources[1][g_indx++] = mSoundPool.load(this, resource, 1);
                    break;
                case "amin" :
                    resource = getResources().getIdentifier(s,
                            "raw", getPackageName());
                    mSoundResources[2][a_indx++] = mSoundPool.load(this, resource, 1);
                    break;
                case "fmin" :
                    resource = getResources().getIdentifier(s,
                            "raw", getPackageName());
                    mSoundResources[3][f_indx++] = mSoundPool.load(this, resource, 1);
                    break;

            }
        }
    }

    /* ----------------------------*/
    /*    interface methods        */

    @Override
    public void soundEvent() {
        mSoundPool.play(mSoundResources[mLowCount][mHighCount], volume, volume, 1, 0, 1f);

        mHighCount++;
        mHighCount %= 9;

        if (mHighCount==0) {
            mLowCount++;
            mLowCount %=4;
        }

    }
}
