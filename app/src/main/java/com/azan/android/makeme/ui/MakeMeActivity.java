package com.azan.android.makeme.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.azan.android.makeme.R;
import com.azan.android.makeme.data.AndroidImageAssets;

public class MakeMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_me);

        if (savedInstanceState == null) {
            //head display
            //Create body part fragment instance
            BodyPartFragment headFragment = new BodyPartFragment();

            //Set the list of Image ids
            headFragment.setImageIds(AndroidImageAssets.getHeads());

            //Get the correct index access in the array of head images from intent
            //default value = 0
            int headIndex = getIntent().getIntExtra("headIndex", 0);
            headFragment.setListIndex(headIndex);

            //Use a FragmentManager
            FragmentManager fragmentManager = getSupportFragmentManager();

            // Fragment transaction
            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();

            //Body display
            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            int bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
            bodyFragment.setListIndex(bodyIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();

            //leg display
            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setImageIds(AndroidImageAssets.getLegs());
            int legIndex = getIntent().getIntExtra("legIndex", 0);
            legFragment.setListIndex(legIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.leg_container, legFragment)
                    .commit();
        }
    }
}
