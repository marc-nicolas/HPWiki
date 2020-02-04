package com.example.hp_wiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hp_wiki.dal.PersonDao;
import com.example.hp_wiki.model.Person;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Übersicht");
        addPersonsToClickableList();
    }

    public void addPersonsToClickableList() {
        ListView persons = findViewById(R.id.personList);
        ArrayAdapter<Person> personAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        personAdapter.addAll(PersonDao.getAll());
        persons.setAdapter(personAdapter);

        AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PersonActivity.class);
                Person selected = (Person)parent.getItemAtPosition(position);
                intent.putExtra("personName", selected.getName());
                startActivity(intent);
            }
        };

        persons.setOnItemClickListener(mListClickedHandler);
    }
}
