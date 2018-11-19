package com.bignerdranch.android.beatbox;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/*
hello world(test)
 */
public class SoundViewModelTest {

    //we need to test sound view model class, so variable with its instance will be called mSubject
    private BeatBox mBeatBox;
    private Sound mSound;
    private SoundViewModel mSubject;

    /*
    @Before - method, which will be called only once before each test executes
    there must be only one method setUp in testClass
     */
    @Before
    public void setUp() {

        //mock(Class) - method to create pseudo instance of class
        mBeatBox = mock(BeatBox.class);

        mSound = new Sound("assetPath");
        mSubject = new SoundViewModel(mBeatBox);
        mSubject.setSound(mSound);
    }

    @Test
    public void exposesSoundNameAsTitle() {
        //seems like IF (soundViewModel.getTitle == mSound.getName)
        assertThat(mSubject.getTitle(), is(mSound.getName()));
    }

    @Test
    public void callsBeatBoxPlayOnButtonClicked() {
        mSubject.onButtonClicked();

        //verify if
        verify(mBeatBox).play(mSound);
    }
}