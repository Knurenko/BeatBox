package com.bignerdranch.android.beatbox;

public class Sound {

    //path_to_file + filename variable
    private String mAssetPath;
    //filename only variable
    private String mName;

    public Sound(String assetPath) {
        mAssetPath = assetPath;

        //split path+name to get array of folder_names and last item will consist filename
        String[] components = assetPath.split("/");
        //extract filename
        String filename = components[components.length - 1];

        mName = filename.replace(".wav", "");
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }

}
