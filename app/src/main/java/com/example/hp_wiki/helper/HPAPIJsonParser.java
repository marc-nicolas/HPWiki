package com.example.hp_wiki.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp_wiki.model.Person;
import com.example.hp_wiki.model.Wand;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class HPAPIJsonParser {

    private static JSONObject jsonArr;
    private static final String API_URL_HPAPI = "http://hp-api.herokuapp.com/api/characters";

    public List<Person> createPersonFromJsonString(Context context) throws JSONException {
        List<Person> persons = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL_HPAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                personJsonParser(response);
                //Badi badi = WieWarmJsonParser.createBadiFromJsonString(response);
                //beckenInfosAdapter.addAll(badi.getBeckenListe());
                //ListView badiInfoList = findViewById(R.id.becken_infos);
                //badiInfoList.setAdapter(beckenInfosAdapter);
                //progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) {
                generateAlertDialog();
            }
        });
        queue.add(stringRequest);



            // Access the RequestQueue through your singleton class.
            //        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
/*
        for(int i = 0; i > 5; i++) {
            JSONObject jsonObj = jsonArr.getJSONObject("name");
            Person person = new Person(
                    jsonObj.getString("name"),
                    jsonObj.getString("species"),
                    jsonObj.getString("gender"),
                    jsonObj.getString("house"),
                    jsonObj.getString("dateOfBirth"),
                    jsonObj.getString("ancestry"),
                    jsonObj.getString("eyeColor"),
                    jsonObj.getString("hairColor"),
                    new Wand(
                            jsonObj.getString("wand.wood"),
                            jsonObj.getString("wand.core"),
                            jsonObj.getInt("wand.length")
                    ),
                    jsonObj.getString("patronus"),
                    jsonObj.getString("actor"),
                    jsonObj.getBoolean("alive"),
                    jsonObj.getString("image")
            );

            if (jsonObj.getBoolean("hogwartsStudent")) {
                person.setRole("Student");
            }

            if (jsonObj.getBoolean("hogwartsStaff")) {
                person.setRole("Hogwarts Staff");
            }
            persons.add(person);
        }
        return persons;
        */
        return null;
    }

    private void personJsonParser(String json) {
        //Log.d("")
    }

    private void generateAlertDialog() {
        Log.d("API Call:", "Failed to get the data.");
    }
}
