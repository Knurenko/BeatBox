package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;

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

    /*  initialize AssetManager in class constructor (thats why we need Context exemplar)
        there is no matter which Context_Object will be called (ApplicationContext, Activity, etc)
        AssetManager object, received by their .getAssets() method will be the same. */
    public BeatBox(Context context) {
        mAssets = context.getAssets();
        loadSounds();
    }

    //soon will return files_list or smtn_else
    private void loadSounds() {
        String[] soundNames;
        try {
            //get all filenames of folder as string array
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(LOG_TAG, "\n Found " + soundNames.length + " sounds");
        } catch (IOException e) {
            Log.e("check", e.toString());
        }
    }
}
