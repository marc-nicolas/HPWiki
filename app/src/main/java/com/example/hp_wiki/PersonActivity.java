package com.example.hp_wiki;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp_wiki.R;
import com.example.hp_wiki.helper.PotterAPIJsonParser;
import com.example.hp_wiki.model.Person;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class PersonActivity extends AppCompatActivity {
    private String name;
    private ProgressBar progressBar;
    private TextView bloodStatus;
    private TextView role;
    private TextView house;
    private TextView patronus;
    private ImageView image;
    private static final String API_URL_HPAPI = "https://hp-api.herokuapp.com/api/characters";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        progressBar = findViewById(R.id.loading_person_details_progress);
        bloodStatus = findViewById(R.id.blood);
        role = findViewById(R.id.role);
        house = findViewById(R.id.house);
        image = findViewById(R.id.image);
        patronus = findViewById(R.id.patronus);
        Intent intent = getIntent();
        name = intent.getStringExtra("personName");
        setTitle(name);
        progressBar.setVisibility(View.VISIBLE);
        getPerson();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getPerson(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL_HPAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Person person = PotterAPIJsonParser.createPersonFromJsonString(response, name);
                    progressBar.setVisibility(View.GONE);
                    bloodStatus.setText(person.getBloodStatus());
                    role.setText("Student");
                    house.setText(person.getHouse());
                    patronus.setText(person.getPatronus());
                    Picasso.get().load(person.getImage()).into(image);
                } catch (JSONException e) {
                    generateAlertDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                generateAlertDialog();
            }
        });
        queue.add(stringRequest);
    }

    private void generateAlertDialog() {
        Log.d("alert", "Could not get data.");
    }

    private List<Person> personJsonParser(String json) {

        JSONArray jsonArray = null;
        List<Person> persons = new ArrayList<>();

        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=0;i<jsonArray.length();i++){

            JSONObject jsonObj = null;
            Person person = null;
            try {
                jsonObj = jsonArray.getJSONObject(i);
                //JSONObject wand = jsonObj.getJSONObject("wand");

/*                person = new Person(
                        jsonObj.getString("name"),
                        jsonObj.getString("species"),
                        jsonObj.getString("gender"),
                        jsonObj.getString("house"),
                        jsonObj.getString("dateOfBirth"),
                        jsonObj.getString("ancestry"),
                        jsonObj.getString("eyeColour"),
                        jsonObj.getString("hairColour"),
                        new Wand(
                                wand.getString("wood"),
                                wand.getString("core"),
                                wand.getInt("length")
                        ),
                        jsonObj.getString("patronus"),
                        jsonObj.getString("actor"),
                        jsonObj.getBoolean("alive"),
                        jsonObj.getString("image")
                );*/
                person = new Person();
                if (jsonObj.getString("name") != null) {
                    person.setName(jsonObj.getString("name"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (person != null) {
                persons.add(person);
            }
        }
        Log.d("Testen", "Zum Testen");
        return persons;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
