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
import com.example.hp_wiki.helper.Capitalisator;
import com.example.hp_wiki.helper.HPAPIJsonParser;
import com.example.hp_wiki.model.House;
import com.example.hp_wiki.model.Person;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.StringJoiner;

public class HouseActivity extends AppCompatActivity {
    private Capitalisator cap = new Capitalisator();

    private static final String API_URL_HPAPI = "https://www.potterapi.com/v1/houses?key=$2a$10$cggq81VeZaQW/8j1bgQhc./UQfKWMSRxCBjBkSMz842XquC7pxiqO";
    private String name;
    private ProgressBar progressBar;
    private TextView mascot;
    private TextView headOfHouse;
    private TextView houseGhost;
    private TextView founder;
    private TextView values;
    private TextView colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        progressBar = findViewById(R.id.loading_house_details_progress);
        mascot = findViewById(R.id.mascot);
        headOfHouse = findViewById(R.id.head_of_house);
        houseGhost = findViewById(R.id.house_ghost);
        founder = findViewById(R.id.founder);
        values = findViewById(R.id.values);
        colors = findViewById(R.id.colors);
        Intent intent = getIntent();
        name = intent.getStringExtra("houseName");
        setTitle(name);
        progressBar.setVisibility(View.VISIBLE);
        getHouse();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getHouse() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL_HPAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                House house = HPAPIJsonParser.createHouseFromJsonString(response, name);
                progressBar.setVisibility(View.GONE);
                mascot.setText(cap.capitalizeFirstLetter(house.getMascot()));
                headOfHouse.setText(house.getHeadOfHouse());
                houseGhost.setText(house.getHouseGhost());
                founder.setText(house.getFounder());
                String str = "";
                for (String value : house.getValues()) {
                    str += cap.capitalizeFirstLetter(value);
                    if (value != house.getValues()[house.getValues().length-1]) {
                        str += ", ";
                    }
                }
                values.setText(str);
                str = "";
                for (String color : house.getColors()) {
                    str += cap.capitalizeFirstLetter(color);
                    if (color != house.getColors()[house.getColors().length-1]) {
                        str += ", ";
                    }
                }
                colors.setText(str);
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
