package com.example.hp_wiki;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp_wiki.helper.HPAPIJsonParser;
import com.example.hp_wiki.model.Person;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PersonActivity extends AppCompatActivity {
    private String name;
    private ProgressBar progressBar;
    private TextView bloodStatus;
    private TextView role;
    private TextView house;
    private TextView patronus;
    private ImageView image;
    private TextView hair_color;
    private TextView eye_color;
    private TextView actour;
    private TextView gender;
    private TextView core;
    private TextView wood;
    private TextView length_wand;
    private TextView core_info;
    private TextView wood_info;
    private TextView wand;
    private TextView length_wand_info;
    private static final String API_URL_HPAPI = "https://hp-api.herokuapp.com/api/characters";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        progressBar = findViewById(R.id.loading_person_details_progress);
        setVariablePerson();
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

    private void setVariablePerson() {
        bloodStatus = findViewById(R.id.blood);
        role = findViewById(R.id.role);
        house = findViewById(R.id.house);
        image = findViewById(R.id.image);
        patronus = findViewById(R.id.patronus);
        hair_color = findViewById(R.id.hair_color);
        eye_color = findViewById(R.id.eye_color);
        actour = findViewById(R.id.actour);
        gender = findViewById(R.id.gender);
        core = findViewById(R.id.core);
        wood = findViewById(R.id.wood);
        length_wand = findViewById(R.id.length_wand);
        core_info = findViewById(R.id.core_info);
        wood_info = findViewById(R.id.wood_info);
        wand = findViewById(R.id.wand);
        length_wand_info = findViewById(R.id.length_wand_info);
    }

    private void setPersonInfos(Person person) {
        bloodStatus.setText(person.getBloodStatus());
        role.setText(person.getRole());
        house.setText(person.getHouse());
        patronus.setText(person.getPatronus());
        hair_color.setText(person.getHairColor());
        eye_color.setText(person.getEyeColor());
        actour.setText(person.getActor());
        gender.setText(person.getGender());
        if (person.getWand() != null) {
            wand.setText("WAND");
            core_info.setText("CORE");
            wood_info.setText("WOOD");
            length_wand_info.setText("LENGTH");
            core.setText(person.getWand().getCore());
            wood.setText(person.getWand().getWood());
            length_wand.setText(String.valueOf(person.getWand().getLength()) + " inch");
        }
        Picasso.get().load(person.getImage()).into(image);
    }

    private void getPerson() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL_HPAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Person person = HPAPIJsonParser.createPersonFromJsonString(response, name);
                    progressBar.setVisibility(View.GONE);
                    setPersonInfos(person);
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

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObj = null;
            Person person = null;
            try {
                jsonObj = jsonArray.getJSONObject(i);
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
