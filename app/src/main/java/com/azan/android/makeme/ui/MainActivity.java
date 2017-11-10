package com.azan.android.makeme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.azan.android.makeme.R;
import com.azan.android.makeme.data.AndroidImageAssets;

//This activity is responsible for displaying master list of all images

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    //variables to store the values for the list index of selected images
    private int headIndex;

    private int bodyIndex;

    private int legIndex;

    //Track whether to display a two-pane or single-pane UI
    // Single-pane for phone screen and two-pane for tablet screen
    private boolean mTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //determine if creating 2-pane or single-pane display
        if(findViewById(R.id.make_me_linear_layout) != null){
            //this LinearLayout initially exist in two-pane tablet case
            mTwoPane = true;

            //change the GridView to space out the images more on tablet
            GridView gridView = findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            //Getting rid of "Next" button appear
            Button nextButton = findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);


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
        } else {
            //Single-pane mode
            mTwoPane = false;
        }
    }


    //Define te behavior for onImageSelected
    public void onImageSelected(int position){
        //create a Toast to display position of clicked
        Toast.makeText(this,"Position clicked = " + position, Toast.LENGTH_SHORT).show();

        //bodyPartNumber will bee = 0 for the head, 1 for body and 2 for leg
        int bodyPartNumber = position /12;

        //Store correct index
        //index will be 0-11
        int listIndex  = position - 12*bodyPartNumber;


        //Handle two-pane case
        if (mTwoPane){
            //create two-pane interaction
            BodyPartFragment newFragment = new BodyPartFragment();

            //set the currently displayed item for correct body part
            switch (bodyPartNumber){
                case 0:
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit();
                    break;
                case 1:
                    newFragment.setImageIds(AndroidImageAssets.getBodies());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setImageIds(AndroidImageAssets.getLegs());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, newFragment)
                            .commit();
                    break;
                default:
                    break;
            }

        } else {

            //set the displayed item for correct body part
            //Single-pane case

            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;
            }
        }

        //Put this information in a bundle and attach to an intent that will launch MakeMeActivity
        Bundle b = new Bundle();
        b.putInt("headIndex", headIndex);
        b.putInt("bodyIndex", bodyIndex);
        b.putInt("legIndex", legIndex);

        //Attach the Bundle to an intent
        final Intent intent = new Intent(this, MakeMeActivity.class);
        intent.putExtras(b);

        //The "Next" button launches a new MakeMeActivity
        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
