package com.bignerdranch.android.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.beatbox.databinding.FragmentBeatBoxBinding;
import com.bignerdranch.android.beatbox.databinding.ListItemSoundBinding;

public class BeatBoxFragment extends Fragment {

    private static final byte SPAN_COUNT = 3;

    // static method to get instance of this fragment from other classes (activities)
    public static BeatBoxFragment newInstance() {

        //will be filled soon
        return new BeatBoxFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
        FragmentBeatBox_Binding - automatically generated file.
        it consist fully hierarchy of views from fragment_beat_box.xml in method getRoot()

        it means that var binding gives us access to views of XML file without findItemById(...)
         */
        FragmentBeatBoxBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_beat_box, container, false);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT));
        binding.recyclerView.setAdapter(new SoundAdapter());

        //return created view (recyclerView)
        return binding.getRoot();
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
        }
    }

    //routine again. Holder was created, now its Adapter's turn
    //using inner class same as holder.
    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            //get inflater from context
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            //use inflater to get listItemSoundBinding
            ListItemSoundBinding binding = DataBindingUtil
                    .inflate(inflater, R.layout.list_item_sound, parent, false);

            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            //todo will be filled soon
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
