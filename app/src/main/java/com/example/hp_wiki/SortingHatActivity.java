package com.example.hp_wiki;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Random;

/*
    EXPLANATION OF THIS ACTIVITY:
    When new Students arrive in Hogwarts, they get sorted into one of the four
    Hogwarts Houses by the Sorting Hat.
    We replicated this scenario by letting the user answer some questions and
    as a result he'll get told which house he would get sorted into.
*/

public class SortingHatActivity extends AppCompatActivity {
    int[] point = {0,0,0,0};
    String[] houses ={"Gryffindor", "Hufflepuff", "Ravenclaw", "Slytherin"};
    int pos;
    String myHouse;
    String house;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting_hat);
        setTitle("Sorting Hat");
        ActionBar actionBar = getSupportActionBar();
        final Button button = findViewById(R.id.findHouse);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findRightHouse();
                showHouse();
                for (int i = 0; i < point.length; i++) {
                    point[i] = 0;
                }
            }
        });
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    // Show the result as a dialog
    public void showHouse() {
        final Context context = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(myHouse + "!");
        builder.setMessage("You're in " + myHouse + "!");
        // The about-button leads the user to the detail page of his house
        builder.setPositiveButton("About " + myHouse, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, HouseActivity.class);
                intent.putExtra("houseName", myHouse);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // If there are multiple houses with the same score, the result will be random between them
    private void findRightHouse(){
        findPoints();
        ArrayList<Integer> positions = findHighest();
        if(positions.size() == 1){
            myHouse = houses[positions.get(0)];
        }
        else{
            Random rnd = new Random();
            int position = rnd.nextInt(positions.size());
            myHouse = houses[positions.get(position)];
        }
    }

    // This method finds the house with the most points
    private ArrayList<Integer> findHighest(){
        ArrayList<Integer> highest = new ArrayList();
        highest.add(0);
        ArrayList<Integer> position = new ArrayList();
        for (int i = 0; i < point.length; i++) {
            if(point[i] > highest.get(0)){
                highest.clear();
                position.clear();
                highest.add(point[i]);
                position.add(i);
            } if(point[i] == highest.get(0)){
                highest.add(point[i]);
                position.add(i);
            }
        }
        return position;
    }

    private void findPoints(){
        // These is one method for each question, so that the answers aren't in order to avoid exploiting the quiz
        countPoints(setQuestion1());
        countPoints(setQuestion2());
        countPoints(setQuestion3());
        countPoints(setQuestion4());
        countPoints(setQuestion5());
        countPoints(setQuestion6());
        countPoints(setQuestion7());
        countPoints(setQuestion8());
        countPoints(setQuestion9());
        countPoints(setQuestion10());
        countPoints(setQuestion11());
    }

    // This method adds a point to whichever house your picked answer belongs to. The house with the most points will be your result
    private void countPoints(String letter){
        switch (letter){
            case "s":
                point[3]++;
                break;
            case "g":
                point[0]++;
                break;
            case "r":
                point[2]++;
                break;
            case "h":
                point[1]++;
                break;
        }
    }

    private String setQuestion1(){
        final Spinner question = findViewById(R.id.question_1);
        pos = question.getSelectedItemPosition();
        switch (pos + 1) {
            case 1:
                house = "s";
                break;
            case 2:
                house = "g";
                break;
            case 3:
                house = "r";
                break;
            case 4:
                house = "h";
                break;
        }
        return house;
    }

    private String setQuestion2(){
        final Spinner question = findViewById(R.id.question_2);
        pos = question.getSelectedItemPosition();
        switch (pos + 1) {
            case 4:
                house = "s";
                break;
            case 2:
                house = "g";
                break;
            case 3:
                house = "r";
                break;
            case 1:
                house = "h";
                break;
        }
        return house;
    }

    private String setQuestion3(){
        final Spinner question = findViewById(R.id.question_3);
        pos = question.getSelectedItemPosition();
        switch (pos + 1) {
            case 2:
                house = "s";
                break;
            case 3:
                house = "g";
                break;
            case 4:
                house = "r";
                break;
            case 1:
                house = "h";
                break;
        }
        return house;
    }

    private String setQuestion4(){
        final Spinner question = findViewById(R.id.question_4);
        pos = question.getSelectedItemPosition();
        switch (pos + 1) {
            case 2:
                house = "s";
                break;
            case 1:
                house = "g";
                break;
            case 4:
                house = "r";
                break;
            case 3:
                house = "h";
                break;
        }
        return house;
    }

    private String setQuestion5(){
        final Spinner question = findViewById(R.id.question_5);
        pos = question.getSelectedItemPosition();
        switch (pos + 1) {
            case 2:
                house = "s";
                break;
            case 1:
                house = "g";
                break;
            case 4:
                house = "r";
                break;
            case 3:
                house = "h";
                break;
        }
        return house;
    }

    private String setQuestion6(){
        final Spinner question = findViewById(R.id.question_6);
        pos = question.getSelectedItemPosition();
        switch (pos + 1) {
            case 3:
                house = "s";
                break;
            case 2:
                house = "g";
                break;
            case 1:
                house = "r";
                break;
            case 4:
                house = "h";
                break;
        }
        return house;
    }

    private String setQuestion7(){
        final Spinner question = findViewById(R.id.question_7);
        pos = question.getSelectedItemPosition();
        switch (pos + 1) {
            case 4:
                house = "s";
                break;
            case 1:
                house = "g";
                break;
            case 2:
                house = "r";
                break;
            case 3:
                house = "h";
                break;
        }
        return house;
    }

    private String setQuestion8(){
        final Spinner question = findViewById(R.id.question_8);
        pos = question.getSelectedItemPosition();
        switch (pos + 1) {
            case 3:
                house = "s";
                break;
            case 2:
                house = "g";
                break;
            case 1:
                house = "r";
                break;
            case 4:
                house = "h";
                break;
        }
        return house;
    }

    private String setQuestion9(){
        final Spinner question = findViewById(R.id.question_9);
        pos = question.getSelectedItemPosition();
        switch (pos + 1) {
            case 2:
                house = "s";
                break;
            case 4:
                house = "g";
                break;
            case 1:
                house = "r";
                break;
            case 3:
                house = "h";
                break;
        }
        return house;
    }

    private String setQuestion10(){
        final Spinner question = findViewById(R.id.question_10);
        pos = question.getSelectedItemPosition();
        switch (pos + 1) {
            case 1:
                house = "s";
                break;
            case 2:
                house = "g";
                break;
            case 3:
                house = "r";
                break;
            case 4:
                house = "h";
                break;
        }
        return house;
    }

    private String setQuestion11(){
        final Spinner question = findViewById(R.id.question_11);
        pos = question.getSelectedItemPosition();
        switch (pos + 1) {
            case 2:
                house = "s";
                break;
            case 4:
                house = "g";
                break;
            case 1:
                house = "r";
                break;
            case 3:
                house = "h";
                break;
        }
        return house;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
