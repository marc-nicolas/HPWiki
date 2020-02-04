package com.example.hp_wiki.ui.spells;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hp_wiki.R;

public class SpellsFragment extends Fragment {

    private SpellsViewModel spellsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        spellsViewModel =
                ViewModelProviders.of(this).get(SpellsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_spells, container, false);
        final TextView textView = root.findViewById(R.id.text_spells);
        spellsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}