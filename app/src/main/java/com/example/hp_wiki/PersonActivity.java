package com.example.hp_wiki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PersonActivity extends AppCompatActivity {

    private static final String API_URL_POTTERAPI = "https://www.potterapi.com/v1/characters?key=$2a$10$cggq81VeZaQW/8j1bgQhc./UQfKWMSRxCBjBkSMz842XquC7pxiqO";
    private static final String API_URL_HPAPI = "http://hp-api.herokuapp.com/api/characters";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
    }
}
