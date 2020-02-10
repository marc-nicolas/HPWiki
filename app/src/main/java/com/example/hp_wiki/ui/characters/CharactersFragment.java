package com.example.hp_wiki.ui.characters;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.hp_wiki.model.Person;
import com.example.hp_wiki.model.Wand;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CharactersFragment extends Fragment {

    private CharactersViewModel charactersViewModel;

    private static final String API_URL_HPAPI = "https://hp-api.herokuapp.com/api/characters";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       charactersViewModel =
         ViewModelProviders.of(this).get(CharactersViewModel.class);

        //charactersViewModel = new CharactersViewModel();

        View root = inflater.inflate(R.layout.fragment_characters, container, false);
        final TextView textView = root.findViewById(R.id.text_characters);
        charactersViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        this.getCharacters();

        /*

        charactersViewModel.getListtest().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                listView.setAdapter(s);
            }
        });
        */

        return root;
    }

    private void getCharacters()
    {
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL_HPAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Person> persons =  personJsonParser(response);
                final ListView listView = getActivity().findViewById(R.id.personList);

                ArrayAdapter<String> personAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
                //personAdapter.addAll(charactersViewModel.getPersons(getContext()));

                List<String> personNames = new LinkedList<String>();
                for (Person p: persons) {
                    personNames.add(p.getName());
                }

                personAdapter.addAll(personNames);
                listView.setAdapter(personAdapter);

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

}