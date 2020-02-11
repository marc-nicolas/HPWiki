package com.example.hp_wiki.helper;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hp_wiki.R;

import java.util.ArrayList;
import java.util.List;

public class Searcher {
    public List<String> search(List<String> list, String query) {
        List<String> filteredList = new ArrayList<>();
        for(String listItem : list){
            if (listItem.toLowerCase().contains(query.toLowerCase())){
                filteredList.add(listItem);
            }
        }
        return filteredList;
    }
}
