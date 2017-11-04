package com.azan.android.makeme.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.azan.android.makeme.R;
import com.azan.android.makeme.data.AndroidImageAssets;

import java.util.List;

/**
 * Created by Aminur Rahman on 4/11/17.
 */

public class BodyPartFragment extends Fragment {

    // Tag for Logging
    private  static final String TAG = "BodyFragment";

    private List<Integer> mImageIds;
    private int mListIndex;

    //Mandatory constructor
    public  BodyPartFragment(){

    }

    /**
     * Inflates the fragment layout and sets image resources
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the MakeMe fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        // Get a reference to the ImageView in the fragment layout
        ImageView imageView = rootView.findViewById(R.id.body_part_image_view);

        //Set the image resource to (ImageView) display
        if (mImageIds != null){
            imageView.setImageResource(mImageIds.get(mListIndex));
        } else {
            Log.v(TAG, "This fragment has no list of image id");
        }

        //Return root view
        return  rootView;
    }

    //Setter for image ids and index

    public void setImageIds(List<Integer> imageIds) {
        mImageIds = imageIds;
    }

    public void setListIndex(int index) {
        mListIndex = index;
    }
}
