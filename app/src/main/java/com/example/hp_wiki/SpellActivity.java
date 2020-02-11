package com.example.hp_wiki;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp_wiki.helper.HPAPIJsonParser;
import com.example.hp_wiki.helper.PotterAPIJsonParser;
import com.example.hp_wiki.model.Person;
import com.example.hp_wiki.model.Spell;

import org.json.JSONException;

public class SpellActivity extends AppCompatActivity {
    private String name;
    private ProgressBar progressBar;
    private TextView type;
    private TextView effect;
    private static final String API_URL_HPAPI = "https://www.potterapi.com/v1/spells?key=$2a$10$cggq81VeZaQW/8j1bgQhc./UQfKWMSRxCBjBkSMz842XquC7pxiqO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell);
        Intent intent = getIntent();
        progressBar = findViewById(R.id.loading_spell_details_progress);
        setVariableSpell();
        name = intent.getStringExtra("spellName");
        setTitle(name);
        progressBar.setVisibility(View.VISIBLE);
        getSpell();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setVariableSpell(){
        type = findViewById(R.id.type);
        effect = findViewById(R.id.effect);
    }

    private void setSpellInfos(Spell spell){
        type.setText(spell.getType());
        effect.setText(spell.getEffect());
    }

    private void getSpell(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL_HPAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Spell spell = PotterAPIJsonParser.createSpellFromJsonString(response, name);
                    setSpellInfos(spell);
                    progressBar.setVisibility(View.GONE);
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

