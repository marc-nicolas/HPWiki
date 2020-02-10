package com.example.hp_wiki;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.hp_wiki.dal.PersonDao;
import com.example.hp_wiki.model.Person;

public class PersonActivity extends AppCompatActivity {

    private int personId;
    private ProgressBar progressBar;

    private static final String API_URL_POTTERAPI = "https://www.potterapi.com/v1/characters?key=$2a$10$cggq81VeZaQW/8j1bgQhc./UQfKWMSRxCBjBkSMz842XquC7pxiqO";
    private static final String API_URL_HPAPI = "http://hp-api.herokuapp.com/api/characters";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_characters);

        //progressBar = findViewById(R.id.loading_person_details_progress);
        Intent intent = getIntent();
        String name = intent.getStringExtra("personsName");
        setTitle(name);
        progressBar.setVisibility(View.VISIBLE);
        //getPersons(API_URL_HPAPI);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        addPersonsToClickableList();

    }

    /*
    private void getPersons(String url) {
        final ArrayAdapter<Person> personInfosAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    List<Person> persons = HPAPIJsonParser.createPersonFromJsonString(response);
                    // verwende die gemerkte Id auf der folgenden Seite
                    ListView personInfoList = findViewById(R.id.personList);
                    personInfoList.setAdapter(personInfosAdapter);
                    progressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    generateAlertDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) {
                generateAlertDialog();
            }
        });
        queue.add(stringRequest);
    }
*/
    private void generateAlertDialog() {
        progressBar.setVisibility(View.GONE);
        AlertDialog.Builder dialogBuilder;
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Closes this activity
                finish();
            }
        });
        dialogBuilder.setMessage("Die Badidetails konnten nicht geladen werden. Versuche es später nochmals.").setTitle("Fehler");
        AlertDialog dialog = dialogBuilder.create(); dialog.show();
    }

    public void addPersonsToClickableList() {
        /*ListView persons = findViewById(R.id.personList);
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
        };*/
    }
}
