package com.bignerdranch.android.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.beatbox.databinding.FragmentBeatBoxBinding;
import com.bignerdranch.android.beatbox.databinding.ListItemSoundBinding;

import java.util.List;

public class BeatBoxFragment extends Fragment {

    private static final byte SPAN_COUNT = 3;

    private BeatBox mBeatBox;

    // static method to get instance of this fragment from other classes (activities)
    public static BeatBoxFragment newInstance() {

        //will be filled soon
        return new BeatBoxFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
        FragmentBeatBox_Binding - automatically generated file.
        it consist fully hierarchy of views from fragment_beat_box.xml in method getRoot()

        it means that var binding gives us access to views of XML file without findItemById(...)
         */
        FragmentBeatBoxBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_beat_box, container, false);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT));
        //now we can get list of sounds from mBeatBox class to adapter
        binding.recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));

        //return created view (recyclerView)
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBeatBox = new BeatBox(getActivity());
    }

    //routine code comes here
    //using inner class instead creating other instance in package cuz in theory class should be small
    private class SoundHolder extends RecyclerView.ViewHolder {

        //list_item_sound_binding - same as FragmentBeatBoxBinding (for description look above)
        private ListItemSoundBinding mBinding;

        //converted public SoundHolder(View itemView)  why private?
        private SoundHolder(ListItemSoundBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(new SoundViewModel(mBeatBox));
        }

        public void bind(Sound sound) {
            mBinding.getViewModel().setSound(sound);
            //exec pending bindings - for faster drawing (maybe like .invalidate() )
            mBinding.executePendingBindings();
        }
    }

    //routine again. Holder was created, now its Adapter's turn
    //using inner class same as holder.
    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {

        //list of sounds to display in recyclerView
        private List<Sound> mSounds;


        //constructor with soundList parameter
        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @Override
        public @NonNull
        SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            //get inflater from context
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            //use inflater to get listItemSoundBinding
            ListItemSoundBinding binding = DataBindingUtil
                    .inflate(inflater, R.layout.list_item_sound, parent, false);

            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
            //Sound sound = mSounds.get(position);
            //holder.bind(sound);

            //try this way, maybe should test both ways for better performance
            holder.bind(mSounds.get(position));
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}
