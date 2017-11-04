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

        //head display
        //Create body part fragment instance
        BodyPartFragment headFragment = new BodyPartFragment();

        //Set the list of Image ids
        headFragment.setImageIds(AndroidImageAssets.getHeads());
        headFragment.setListIndex(1);

        //Use a FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Fragment transaction
        fragmentManager.beginTransaction()
                .add(R.id.head_container, headFragment)
                .commit();

        //Body display

        //Create body part fragment instance
        BodyPartFragment bodyFragment = new BodyPartFragment();

        //Set the list of Image ids
        bodyFragment.setImageIds(AndroidImageAssets.getBodies());
        bodyFragment.setListIndex(1);

        // Fragment transaction
        fragmentManager.beginTransaction()
                .add(R.id.body_container, bodyFragment)
                .commit();

        //leg display
        //Create body part fragment instance
        BodyPartFragment legFragment = new BodyPartFragment();

        //Set the list of Image ids
        legFragment.setImageIds(AndroidImageAssets.getLegs());
        legFragment.setListIndex(1);

        // Fragment transaction
        fragmentManager.beginTransaction()
                .add(R.id.leg_container, legFragment)
                .commit();
    }
}
