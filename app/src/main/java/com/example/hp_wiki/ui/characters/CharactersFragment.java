package com.example.hp_wiki.ui.characters;

import android.os.Bundle;
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

import com.example.hp_wiki.R;
import com.example.hp_wiki.model.Person;

import java.util.List;

public class CharactersFragment extends Fragment {

    private CharactersViewModel charactersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       charactersViewModel =
         ViewModelProviders.of(this).get(CharactersViewModel.class);

        //charactersViewModel = new CharactersViewModel();

        View root = inflater.inflate(R.layout.fragment_characters, container, false);
        /*final TextView textView = root.findViewById(R.id.text_characters);
        charactersViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        final ListView listView = root.findViewById(R.id.personList);

        ArrayAdapter<String> personAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        personAdapter.addAll(charactersViewModel.getPersons(getContext()));
        listView.setAdapter(personAdapter);
        */
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
}