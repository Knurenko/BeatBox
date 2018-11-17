package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
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

    //we need asset manager to work with assets
    private AssetManager mAssets;
    //list variable to store sounds (filenames only or smtn else?)
    private List<Sound> mSounds = new ArrayList<>();

    /*  initialize AssetManager in class constructor (thats why we need Context exemplar)
        there is no matter which Context_Object will be called (ApplicationContext, Activity, etc)
        AssetManager object, received by their .getAssets() method will be the same. */
    public BeatBox(Context context) {
        mAssets = context.getAssets();
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
                mSounds.add(sound);
            }
            //end of replaced code

        } catch (IOException e) {
            Log.e("check", e.toString());
        }

        //replaced code was here (bignerdbranch, wtf?)
    }

    public List<Sound> getSounds() {
        return mSounds;
    }
}
