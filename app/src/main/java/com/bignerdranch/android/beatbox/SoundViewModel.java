package com.bignerdranch.android.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

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

UPD:
to display file_names correctly without mixing while scrolling
we need to implement Observable interface & override all things
but there's easier way:
    - make SoundViewModel be inherited from BaseObservable class
    - pin methods, using in binding with @Bindable annotation
    - call notifyChange() or notifyPropertyChanged(int propertyId) each time changes appears
 */
public class SoundViewModel extends BaseObservable {
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
        notifyChange();
    }

    //title getter (model.Name -> VM -> view.Text)
    @Bindable
    public String getTitle() {
        return mSound.getName();
    }
}
