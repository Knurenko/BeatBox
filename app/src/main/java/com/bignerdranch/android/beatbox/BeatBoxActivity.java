package com.bignerdranch.android.beatbox;

import android.support.v4.app.Fragment;

public class BeatBoxActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {

        //create new instance of BeatBoxFragment and sent it to SingleFragmentActivity as Fragment
        //where it uses in onCreate by FragmentManager 
        return BeatBoxFragment.newinstance();
    }
}
