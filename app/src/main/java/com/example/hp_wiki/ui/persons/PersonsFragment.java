package com.example.hp_wiki.ui.persons;


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
import com.example.hp_wiki.MainActivity;
import com.example.hp_wiki.PersonActivity;
import com.example.hp_wiki.R;
import com.example.hp_wiki.helper.ErrorHandler;
import com.example.hp_wiki.helper.Searcher;
import com.example.hp_wiki.model.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PersonsFragment extends Fragment {

    private PersonsViewModel personsViewModel;

    private String activeHouse = "House";
    private String activeBloodStatus = "Blood Status";

    private Searcher searcher = new Searcher();

    private static final String API_URL_HPAPI = "https://hp-api.herokuapp.com/api/characters";
    private List<Person> persons = new ArrayList<>();
    private List<String> personNames;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        personsViewModel =
                ViewModelProviders.of(this).get(PersonsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_persons, container, false);
        addSearchListener(root);
        addHouseFilterListener(root);
        addBloodFilterListener(root);

        this.getPersons();

        return root;
    }

    private void addSearchListener(final View root) {
        final EditText search = root.findViewById(R.id.search_persons);
        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {
                final ListView listView = getActivity().findViewById(R.id.personList);
                ArrayAdapter<String> personAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
                personAdapter.addAll(searcher.search(personNames, s.toString()));
                listView.setAdapter(personAdapter);
/*                final ListView listView = getActivity().findViewById(R.id.personList);

                List<String> filteredList = new ArrayList<>();
                List<String> searchedList = searcher.search(personNames, s.toString());

                for (int i = 0; i < searchedList.size(); i++) {
                    if (activeHouse.equals("House") && activeBloodStatus.equals("Blood Status")) {
                        filteredList = searchedList;
                    } else {
                        for (int j = 0; j < persons.size(); j++) {
                            if (persons.get(j).getName().equals(searchedList.get(i))) {
                                if (activeHouse.equals("House") && !activeBloodStatus.equals("Blood Status")) {
                                    if (persons.get(j).getBloodStatus().equals(activeBloodStatus)) {
                                        filteredList.add(searchedList.get(i));
                                    }
                                } else if (!activeHouse.equals("House") && activeBloodStatus.equals("Blood Status")) {
                                    if (persons.get(j).getHouse().equals(activeHouse)) {
                                        filteredList.add(searchedList.get(i));
                                    }
                                } else {
                                    if (persons.get(j).getHouse().equals(activeHouse) && persons.get(j).getBloodStatus().equals(activeBloodStatus)) {
                                        filteredList.add(searchedList.get(i));
                                    }
                                }
                            }
                        }
                    }
                }
                ArrayAdapter<String> personAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
                listView.setAdapter(personAdapter);
                personAdapter.addAll(filteredList);*/
            }
        });
    }

    private void addHouseFilterListener(View root) {
        final Spinner houseFilter = root.findViewById(R.id.house_filters);

        houseFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                activeHouse = houseFilter.getSelectedItem().toString();
                final ListView listView = getActivity().findViewById(R.id.personList);
                List<String> filteredList = new ArrayList<>();

                for (int i = 0; i < persons.size(); i++) {
                    if (houseFilter.getSelectedItem().toString().equals("House")) {
                        filteredList = personNames;
                    }
                    if (persons.get(i).getHouse().equals(houseFilter.getSelectedItem().toString())) {
                        filteredList.add(personNames.get(i));
                    }
                }
                ArrayAdapter<String> personAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
                personAdapter.addAll(filteredList);
                listView.setAdapter(personAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    private void addBloodFilterListener(View root) {
        final Spinner bloodFilter = root.findViewById(R.id.blood_filters);

        bloodFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                activeBloodStatus = bloodFilter.getSelectedItem().toString();
                final ListView listView = getActivity().findViewById(R.id.personList);
                List<String> filteredList = new ArrayList<>();

                for (int i = 0; i < persons.size(); i++) {
                    if (bloodFilter.getSelectedItem().toString().equals("Blood Status")) {
                        filteredList = personNames;
                    }
                    if (persons.get(i).getBloodStatus().equals(bloodFilter.getSelectedItem().toString().toLowerCase())) {
                        filteredList.add(personNames.get(i));
                    }
                }
                ArrayAdapter<String> personAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
                personAdapter.addAll(filteredList);
                listView.setAdapter(personAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    // Gets Persons from API
    private void getPersons() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL_HPAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                persons = personJsonParser(response);
                final ListView listView = getActivity().findViewById(R.id.personList);

                ArrayAdapter<String> personAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
                //personAdapter.addAll(personsViewModel.getPersons(getContext()));

                personNames = new LinkedList<String>();
                for (Person p : persons) {
                    personNames.add(p.getName());
                }

                personAdapter.addAll(personNames);
                listView.setAdapter(personAdapter);
                AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView parent, View v, int position, long id) {
                        Intent intent = new Intent(getContext(), PersonActivity.class);
                        String selected = (String) parent.getItemAtPosition(position);
                        intent.putExtra("personName", selected);
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

    // Adds names of the Persons to a List
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
                if (jsonObj.getString("house") != null) {
                    person.setHouse(jsonObj.getString("house"));
                }
                if (jsonObj.getString("ancestry") != null) {
                    person.setBloodStatus(jsonObj.getString("ancestry"));
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
}
