package com.azan.android.makeme.ui;

//This Fragment displays all of the images in a large GridView list

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.azan.android.makeme.R;
import com.azan.android.makeme.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {

    //Define a new interface OnImageClickListener that triggers a callback in the host activity
    OnImageClickListener mCallback;

    public  interface OnImageClickListener{
        void onImageSelected(int position);
    }

    //Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mCallback = (OnImageClickListener) context;
        } catch (ClassCastException e) {
            throw  new  ClassCastException(context.toString()
                    + " must implement OnImageClickListener");
        }
    }

    //mandatory constructor
    public  MasterListFragment(){

    }

    // Inflates the GridView of all images

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_master_list, container,
                false);

        // Get a reference to the GridView in the fragment_master_list.xml layout file
        GridView gridView = rootView.findViewById(R.id.images_grid_view);

        //Create the adapter
        // This adapter takes context and ArrayList of all the image resources to display
        MasterListAdapter mAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        //Set the adapter on GridView
        gridView.setAdapter(mAdapter);

        //Set a click listener on the gridView and trigger the callback onImageSelected
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mCallback.onImageSelected(position);
            }
        });

        return  rootView;
    }
}
