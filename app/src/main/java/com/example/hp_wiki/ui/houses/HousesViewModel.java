package com.example.hp_wiki.ui.houses;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HousesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HousesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is houses fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}