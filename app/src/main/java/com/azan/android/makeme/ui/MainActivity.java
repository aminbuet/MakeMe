package com.azan.android.makeme.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.azan.android.makeme.R;

//This activity is responsible for displaying master list of all images

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Define te behavior for onImageSelected
    public void onImageSelected(int position){
        //create a Toast to display position of clicked
        Toast.makeText(this,"Position clicked = " + position, Toast.LENGTH_SHORT).show();
    }
}
