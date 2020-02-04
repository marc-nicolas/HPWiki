package com.example.hp_wiki.ui.characters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CharactersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CharactersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is characters fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}