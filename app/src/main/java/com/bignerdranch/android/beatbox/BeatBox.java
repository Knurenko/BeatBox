package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
class for handling assets
basic functions:
    -search
    -loading
    -play
(or maybe not)
 */
public class BeatBox {
    //LogTag const
    private static final String LOG_TAG = "check";
    //folder const
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    //we need asset manager to work with assets
    private AssetManager mAssets;
    //list variable to store sounds (filenames only or smtn else?)
    private List<Sound> mSounds = new ArrayList<>();
    //soundPool instance for storing and processing a large number of sounds
    private SoundPool mSoundPool;

    /*  initialize AssetManager in class constructor (that's why we need Context exemplar)
        there is no matter which Context_Object will be called (ApplicationContext, Activity, etc)
        AssetManager object, received by their .getAssets() method will be the same. */
    public BeatBox(Context context) {
        mAssets = context.getAssets();
        //This constructor is deprecated, but it is needed for compatibility.
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    //soon will return files_list or smtn_else
    private void loadSounds() {
        //seems like need to init array much correctly to avoid exceptions/errors
        String[] soundNames;

        try {
            //get all filenames of folder as string array
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(LOG_TAG, "\n Found " + soundNames.length + " sounds");

            //replaced code below into block 'try' to avoid "variable soundNames might not have been initialized" ERROR.
            //initialize list of sounds
            for (String filename : soundNames) {
                //concat folder + filename
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                //init sound list
                Sound sound = new Sound(assetPath);
                //add sound to SoundPool
                load(sound);
                mSounds.add(sound);
            }
            //end of replaced code

        } catch (IOException e) {
            Log.e(LOG_TAG, "\n Couldn't load sound, see error detail: \n" + e.getMessage());
        }

        //replaced code was here (bignerdbranch, wtf?)
    }

    private void load(Sound sound) throws IOException {
        //get file path in file descriptor
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        //load sounds to soundPool
        int soundId = mSoundPool.load(afd, 1);
        //set ID
        sound.setSoundId(soundId);
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    public void play(Sound sound) {
        //Integer - to handle null values
        Integer soundId = sound.getSoundId();
        if (soundId == null) {
            return;
        }
        //YEAH c'mon play dat shit DJ!
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
