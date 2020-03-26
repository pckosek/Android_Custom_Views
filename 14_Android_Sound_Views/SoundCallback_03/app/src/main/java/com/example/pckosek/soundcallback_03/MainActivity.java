package com.example.pckosek.soundcallback_03;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.Random;

public class MainActivity extends AppCompatActivity  {

    MySoundView mySoundView;
    public SoundEventListener mSoundEventListener;

    private SoundPool mSoundPool;
    float actVolume, maxVolume, volume;
    AudioManager audioManager;

    int[][] mSoundResources = new int[4][9];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        mSoundEventListener = new SoundEventListener();

        mySoundView = findViewById(R.id.sound_view);
        mySoundView.setSoundViewListener(mSoundEventListener);
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
    /*    custom helper class      */


    public class SoundEventListener implements MySoundView.SoundViewListener, Runnable {

        private Handler mHandler = new Handler();
        private int mKeyCount  = 0;
        private int mArpaggioStepCount = 0;

        private int[] noteSteps = {0,1,2,3,4,5,6,7,8};

        private void reset() {
            mKeyCount  = 0;
            mArpaggioStepCount = 0;
            shuffleArray(noteSteps);
        }

        @Override
        public void startPlaying() {
            reset();
            mHandler.post(this);
        }

        @Override
        public void stopPlaying() {
            mHandler.removeCallbacks(this);
        }

        @Override
        public void run() {
            mSoundPool.play(mSoundResources[mKeyCount]
                    [noteSteps[mArpaggioStepCount]], volume, volume, 1, 0, 1f);

            mArpaggioStepCount++;
            mArpaggioStepCount %= 9;

            if (mArpaggioStepCount==0) {
                mKeyCount++;
                mKeyCount %=4;
            }
            mHandler.postDelayed(this, 125);
        }

        // --> https://stackoverflow.com/questions/1519736
        private void shuffleArray(int[] array)
        {
            int index;
            Random random = new Random();
            for (int i = array.length - 1; i > 0; i--)
            {
                index = random.nextInt(i + 1);
                if (index != i)
                {
                    array[index] ^= array[i];
                    array[i] ^= array[index];
                    array[index] ^= array[i];
                }
            }
        }
    }
}
