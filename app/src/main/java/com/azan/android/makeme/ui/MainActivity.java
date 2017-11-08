package com.azan.android.makeme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.azan.android.makeme.R;

//This activity is responsible for displaying master list of all images

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    //variables to store the values for the list index of selected images
    private int headIndex;

    private int bodyIndex;

    private int legIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        //set the displayed item for correct body part

        switch(bodyPartNumber) {
            case 0 : headIndex = listIndex;
                break;
            case 1 : bodyIndex = listIndex;
                break;
            case 2 : legIndex = listIndex;
                break;
            default: break;
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
