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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aminur Rahman on 4/11/17.
 */

public class BodyPartFragment extends Fragment {

    //Final Strings to store store information about the list of images and list of index
    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";

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

        //Load the saved state (the list of images and list index) if there is one
        if (savedInstanceState != null){
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        //Inflate the MakeMe fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        // Get a reference to the ImageView in the fragment layout
        final ImageView imageView = rootView.findViewById(R.id.body_part_image_view);

        //Set the image resource to (ImageView) display
        if (mImageIds != null){
            imageView.setImageResource(mImageIds.get(mListIndex));

            //Set a click Listener on imageView
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Increment position as long as the index remains <= the size of the image ids
                    if (mListIndex < mImageIds.size() - 1){
                        mListIndex ++;
                    } else {
                        //The end of the list start from beginning
                        mListIndex = 0;
                    }
                    //Set te image resource to new list item
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
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

    /**
     * Save the current state of the fragment
     */
    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mListIndex);

    }
}
