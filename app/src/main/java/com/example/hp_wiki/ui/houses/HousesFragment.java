package com.example.hp_wiki.ui.houses;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp_wiki.HouseActivity;
import com.example.hp_wiki.R;
import com.example.hp_wiki.SortingHatActivity;
import com.example.hp_wiki.helper.ErrorHandler;
import com.example.hp_wiki.model.House;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HousesFragment extends Fragment {

    private HousesViewModel housesViewModel;

    private static final String API_URL_POTTERAPI = "https://www.potterapi.com/v1/houses?key=$2a$10$cggq81VeZaQW/8j1bgQhc./UQfKWMSRxCBjBkSMz842XquC7pxiqO";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        housesViewModel = ViewModelProviders.of(this).get(HousesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_houses, container, false);
        final Button button = root.findViewById(R.id.sorting_hat);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent intent = new Intent(getContext(), SortingHatActivity.class);
                startActivity(intent);
            }
        });

        getHouses();
        return root;
    }

    private void getHouses() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL_POTTERAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<House> houses = housesJsonParser(response);
                final ListView listView = getActivity().findViewById(R.id.houseList);

                ArrayAdapter<String> houseAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
                List<String> houseNames = new LinkedList<String>();
                for (House h : houses) {
                    houseNames.add(h.getName());
                }

                houseAdapter.addAll(houseNames);
                listView.setAdapter(houseAdapter);

                AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView parent, View v, int position, long id) {
                        Intent intent = new Intent(getContext(), HouseActivity.class);
                        String selected = (String) parent.getItemAtPosition(position);
                        intent.putExtra("houseName", selected);
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
    }

    private List<House> housesJsonParser(String json) {

        JSONArray jsonArray = null;
        List<House> houses = new ArrayList<>();

        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObj = null;
            House house = null;
            try {
                jsonObj = jsonArray.getJSONObject(i);

                house = new House();
                if (jsonObj.getString("name") != null) {
                    house.setName(jsonObj.getString("name"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (house != null) {
                houses.add(house);
            }
        }
        return houses;
    }

}