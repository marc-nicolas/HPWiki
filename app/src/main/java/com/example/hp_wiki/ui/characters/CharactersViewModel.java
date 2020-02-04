package com.example.hp_wiki.ui.characters;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hp_wiki.helper.HPAPIJsonParser;
import com.example.hp_wiki.model.Person;

import org.json.JSONException;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class CharactersViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<String>> mListTest;
    private List<String> listtest;
    private List<Person> persons;

    public CharactersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("lol");

        listtest = new LinkedList<String>();
        this.loadTest();
        loadPersons();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<String>> getListtest() {
        if(mListTest == null){
            mListTest = new MutableLiveData<>();
            loadTest();
        }
        return  mListTest;
    }

    public List<String> getList() {
        return  listtest;
    }

    private void loadTest() {
        listtest.add("haud");
        listtest.add("qef");
       // mListTest.setValue(listtest);
    }

    public LiveData<List<String>> getPersonsLiveData() {
        if(mListTest == null){
            mListTest = new MutableLiveData<>();
            loadTest();
        }
        return  mListTest;
    }

    public List<String> getPersons(Context context) {
        try {
            HPAPIJsonParser parser = new HPAPIJsonParser();
            persons = parser.createPersonFromJsonString(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  listtest;
    }

    private void loadPersons() {


    }
}