package com.bignerdranch.android.beatbox;

/*
VIEW MODEL CLASS
(final part of MVVM pattern, maybe)

this type of class will be intermediate between Model_Classes and Views (with data bindings)
later in view files will appear attr like:
<data>
    <variable
        name="var_name"
        type="package_name.SoundViewModel"/>
</data>

then in XML will be used BINDING OPERATOR (!!!)
binding operator syntax: @{some_commands}
like:
android:checked="@{creepViewModel.isDead()}"
where 'creepViewModel' - view model class; .isDead() - bool method of that class.

so in view model class .isDead() method should be like:

public boolean isDead() {
    return mCreep.isDead();
}

WHY we need that middle level?
Steve McConnell "Code Complete"
 */

public class SoundViewModel {
    //sound - contact with model
    //beatbox - contact with asset manager
    private Sound mSound;
    private BeatBox mBeatBox;

    public SoundViewModel(BeatBox beatBox) {
        mBeatBox = beatBox;
    }

    //get + set sound
    public Sound getSound() {
        return mSound;
    }

    public void setSound(Sound sound) {
        mSound = sound;
    }

    //title getter (model.Name -> VM -> view.Text)
    public String getTitle() {
        return mSound.getName();
    }
}
