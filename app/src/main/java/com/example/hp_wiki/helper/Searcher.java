package com.example.hp_wiki.helper;
import java.util.ArrayList;
import java.util.List;

public class Searcher {
    public List<String> search(List<String> list, String query) {
        // Makes a new list with only the elements, which include the search query
        List<String> filteredList = new ArrayList<>();
        for (String listItem : list) {
            if (listItem.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(listItem);
            }
        }
        return filteredList;
    }
}
