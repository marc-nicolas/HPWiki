package com.example.hp_wiki.helper;

import com.example.hp_wiki.model.Person;
import com.example.hp_wiki.model.Wand;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HPAPIJsonParser {
    private static Person person;

    //Get current person from person API
    public static Person createPersonFromJsonString(String personJsonString, String name) throws JSONException {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(personJsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArray.length(); i++) {
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
        //If the hogwartsStudent or the hogwartsStaff is equal true
        // the character is a student or a hogwarts staff
        if (jsonObj.getBoolean("hogwartsStudent") == true || jsonObj.getBoolean("hogwartsStaff") == true) {
            if (jsonObj.getBoolean("hogwartsStudent") == true) {
                person.setRole("Student");
            } else {
                person.setRole("Hogwarts Staff");
            }
        } else {
            person.setRole("Unknow");
        }
        person.setSpecies(jsonObj.getString("species"));
        person.setGender(jsonObj.getString("gender"));
        person.setHouse(jsonObj.getString("house"));
        person.setDateOfBirth(jsonObj.getString("dateOfBirth"));
        person.setBloodStatus(jsonObj.getString("ancestry"));
        person.setEyeColor(jsonObj.getString("eyeColour"));
        person.setHairColor(jsonObj.getString("hairColour"));
        person.setPatronus(jsonObj.getString("patronus"));
        person.setActor(jsonObj.getString("actor"));
        person.setAlive(jsonObj.getBoolean("alive"));
        person.setImage(jsonObj.getString("image"));
        JSONObject wandJson = jsonObj.getJSONObject("wand");
        Wand wand = new Wand();
        if (!wandJson.getString("wood").equals("")) {
            setWand(person, wand);
            wand.setWood(wandJson.getString("wood"));
        }
        if (!wandJson.getString("core").equals("")) {
            setWand(person, wand);
            wand.setCore(wandJson.getString("core"));
        }
        if (wandJson.getInt("length") > 0) {
            setWand(person, wand);
            wand.setLength(wandJson.getInt("length"));
        }
    }

    //assign the wand to the person
    private static void setWand(Person person, Wand wand) {
        if (person.getWand() == null) {
            person.setWand(wand);
        }
    }

}

