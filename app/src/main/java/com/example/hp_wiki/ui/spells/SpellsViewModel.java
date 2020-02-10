package com.example.hp_wiki.ui.spells;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SpellsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SpellsViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is spells fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}