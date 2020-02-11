package com.example.hp_wiki.helper;


import com.example.hp_wiki.model.Person;
import com.example.hp_wiki.model.Wand;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PotterAPIJsonParser {
    private static Person person;

    public static Person createPersonFromJsonString(String personJsonString, String name) throws JSONException {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(personJsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i=0;i<jsonArray.length();i++) {
            try {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                if (jsonObj.getString("name").equals(name)) {
                    setPerson(jsonObj);
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return person;
    }

    private static void setPerson(JSONObject jsonObj) throws JSONException {
        person = new Person();
        person.setName(jsonObj.getString("name"));
        if(jsonObj.getString("species") != null){
            person.setSpecies(jsonObj.getString("species"));
        }
        if(jsonObj.getString("gender") != null){
            person.setGender(jsonObj.getString("gender"));
        }
        if(jsonObj.getString("house") != null){
            person.setHouse(jsonObj.getString("house"));
        }
        if(jsonObj.getString("dateOfBirth") != null){
            person.setDateOfBirth(jsonObj.getString("dateOfBirth"));
        }
        if(jsonObj.getString("ancestry") != null){
            person.setBloodStatus(jsonObj.getString("ancestry"));
        }
        if(jsonObj.getString("eyeColour") != null){
            person.setEyeColor(jsonObj.getString("eyeColour"));
        }
        if(jsonObj.getString("hairColour") != null){
            person.setHairColor(jsonObj.getString("hairColour"));
        }
        if(jsonObj.getString("patronus") != null){
            person.setPatronus(jsonObj.getString("patronus"));
        }
        if(jsonObj.getString("actor") != null){
            person.setActor(jsonObj.getString("actor"));
        }
        if(jsonObj.getBoolean("alive")){
            person.setAlive(jsonObj.getBoolean("alive"));
        }
        if(jsonObj.getString("image") != null){
            person.setImage(jsonObj.getString("image"));
        }
        JSONObject wandJson = jsonObj.getJSONObject("wand");
        Iterator keys = wandJson.keys();
        while (keys.hasNext()) {
            Wand wand = new Wand();
            String key = (String) keys.next();
            JSONObject subObj = wandJson.getJSONObject(key);
            if(jsonObj.getString("wood") != null){
                wand.setWood(jsonObj.getString("wood"));
            }
            if(jsonObj.getString("core") != null){
                wand.setCore(jsonObj.getString("core"));
            }
            if(jsonObj.getInt("length") != 0){
                wand.setLength(jsonObj.getInt("length"));
            }
            person.setWand(wand);
        }
    }
}

