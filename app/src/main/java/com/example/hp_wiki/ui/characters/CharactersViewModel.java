package com.example.hp_wiki.ui.characters;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hp_wiki.model.Person;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CharactersViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<String>> mListTest;
    private List<String> listtest;
    private List<Person> persons = new ArrayList<>();

    public CharactersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("fwef");

        listtest = new LinkedList<String>();
        //loadPersons();
    }

    public LiveData<String> getText() {
        return mText;
    }


    private void loadTest() {
        //listtest.add("haud");
        //listtest.add("qef");
       // mListTest.setValue(listtest);
    }

    /*public LiveData<List<String>> getPersonsLiveData() {
        if(mListTest == null){
            mListTest = new MutableLiveData<>();
            //            loadTest();
        }
        return mListTest;
    }*/

/*
    public List<String> getPersons(Context context) {



        HPAPIJsonParser parser = new HPAPIJsonParser();
        try {
            persons = parser.createPersonFromJsonString(context);
            for(Person person : persons){
                Log.d("Person", person.getName());
            }
        } catch (JSONException e) {
            Log.d("Error", "error");
            e.printStackTrace();
        }
        for (Person person : persons) {
            Log.d("name: ", person.getName());
            listtest.add(person.getName());
        }
        return listtest;
    }

    private void loadPersons() {

    }*/
}