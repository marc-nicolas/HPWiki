package com.example.hp_wiki.ui.spells;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp_wiki.R;
import com.example.hp_wiki.SpellActivity;
import com.example.hp_wiki.helper.ErrorHandler;
import com.example.hp_wiki.helper.Searcher;
import com.example.hp_wiki.model.Spell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SpellsFragment extends Fragment {

    private static final String API_URL_POTTERAPI = "https://www.potterapi.com/v1/spells?key=$2a$10$cggq81VeZaQW/8j1bgQhc./UQfKWMSRxCBjBkSMz842XquC7pxiqO";

    private SpellsViewModel spellsViewModel;
    private Searcher searcher = new Searcher();

    private List<Spell> spells = new ArrayList<>();
    private List<String> spellNames;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        spellsViewModel = ViewModelProviders.of(this).get(SpellsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_spells, container, false);

        getSpells();
        addSearchListener(root);
        addTypeFilterListener(root);

        return root;
    }

    private void addSearchListener(View root) {
        final EditText search = root.findViewById(R.id.search_spells);
        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {
                final ListView listView = getActivity().findViewById(R.id.spellList);
                ArrayAdapter<String> spellAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
                spellAdapter.addAll(searcher.search(spellNames, s.toString()));
                listView.setAdapter(spellAdapter);
            }
        });
    }

    private void addTypeFilterListener(View root) {
        final Spinner spellFilter = root.findViewById(R.id.spell_filters);

        spellFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                final ListView listView = getActivity().findViewById(R.id.spellList);
                List<String> filteredList = new ArrayList<>();

                for (int i = 0; i < spells.size(); i++) {
                    if (spellFilter.getSelectedItem().toString().equals("Type")) {
                        filteredList = spellNames;
                    }
                    if (spells.get(i).getType().equals(spellFilter.getSelectedItem().toString())) {
                        filteredList.add(spellNames.get(i));
                    }
                }
                ArrayAdapter<String> spellAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
                spellAdapter.addAll(filteredList);
                listView.setAdapter(spellAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    // Gets Spells from API
    private void getSpells() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL_POTTERAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Spell> spells = spellsJsonParser(response);
                final ListView listView = getActivity().findViewById(R.id.spellList);

                ArrayAdapter<String> spellAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
                spellNames = new LinkedList<String>();
                for (Spell s : spells) {
                    spellNames.add(s.getName());
                }

                spellAdapter.addAll(spellNames);
                listView.setAdapter(spellAdapter);
                AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView parent, View v, int position, long id) {
                        Intent intent = new Intent(getContext(), SpellActivity.class);
                        String selected = (String) parent.getItemAtPosition(position);
                        intent.putExtra("spellName", selected);
                        startActivity(intent);
                    }
                };
                listView.setOnItemClickListener(mListClickedHandler);

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
        ErrorHandler errorHandler = new ErrorHandler(getActivity());
        errorHandler.alertApiError();
        Log.d("alert", "Could not get data.");
    }

    // Adds names of the Spells to a List
    private List<Spell> spellsJsonParser(String json) {

        JSONArray jsonArray = null;
        spells = new ArrayList<>();

        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObj = null;
            Spell spell = null;
            try {
                jsonObj = jsonArray.getJSONObject(i);

                spell = new Spell();
                if (jsonObj.getString("spell") != null) {
                    spell.setName(jsonObj.getString("spell"));
                }
                if (jsonObj.getString("type") != null) {
                    spell.setType(jsonObj.getString("type"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (spell != null) {
                spells.add(spell);
            }
        }
        return spells;
    }

}