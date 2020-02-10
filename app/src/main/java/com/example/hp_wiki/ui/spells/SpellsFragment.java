package com.example.hp_wiki.ui.spells;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp_wiki.R;
import com.example.hp_wiki.model.Spell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SpellsFragment extends Fragment {

    private SpellsViewModel spellsViewModel;

    private static final String API_URL_POTTERAPI = "https://www.potterapi.com/v1/spells?key=$2a$10$cggq81VeZaQW/8j1bgQhc./UQfKWMSRxCBjBkSMz842XquC7pxiqO";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        spellsViewModel =
                ViewModelProviders.of(this).get(SpellsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_spells, container, false);

        this.getSpells();
        return root;
    }

    private void getSpells() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL_POTTERAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Spell> spells =  spellsJsonParser(response);
                final ListView listView = getActivity().findViewById(R.id.spellList);

                ArrayAdapter<String> spellAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
                List<String> spellNames = new LinkedList<String>();
                for (Spell s: spells) {
                    spellNames.add(s.getName());
                }

                spellAdapter.addAll(spellNames);
                listView.setAdapter(spellAdapter);

            }
        }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) {
                generateAlertDialog();
            }
        });
        queue.add(stringRequest);
    }

    private void generateAlertDialog() {
        Log.d("alert", "Could not get data.");
    }

    private List<Spell> spellsJsonParser(String json) {

        JSONArray jsonArray = null;
        List<Spell> spells = new ArrayList<>();

        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=0;i<jsonArray.length();i++){

            JSONObject jsonObj = null;
            Spell spell = null;
            try {
                jsonObj = jsonArray.getJSONObject(i);

                spell = new Spell();
                if (jsonObj.getString("spell") != null) {
                    spell.setName(jsonObj.getString("spell"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (spell != null) {
                spells.add(spell);
            }
        }
        Log.d("Testen", "Zum Testen");
        return spells;
    }

}