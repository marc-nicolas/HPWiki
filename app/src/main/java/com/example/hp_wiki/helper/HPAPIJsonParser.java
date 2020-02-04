package com.example.hp_wiki.helper;

import com.example.hp_wiki.model.Person;
import com.example.hp_wiki.model.Wand;

import org.json.JSONException;
import org.json.JSONObject;


public class HPAPIJsonParser {

    public static Person createPersonFromJsonString(String badiJsonString) throws JSONException {
        JSONObject jsonObj = new JSONObject(badiJsonString);

        Person person = new Person(
                jsonObj.getString("name"),
                jsonObj.getString("species"),
                jsonObj.getString("gender"),
                jsonObj.getString("house"),
                jsonObj.getString("dateOfBirth"),
                jsonObj.getString("ancestry"),
                jsonObj.getString("eyeColor"),
                jsonObj.getString("hairColor"),
                new Wand(
                        jsonObj.getString("wand.wood"),
                        jsonObj.getString("wand.core"),
                        jsonObj.getInt("wand.length")
                ),
                jsonObj.getString("patronus"),
                jsonObj.getString("actor"),
                jsonObj.getBoolean("alive"),
                jsonObj.getString("image")
        );

        if (jsonObj.getBoolean("hogwartsStudent")) {
            person.setRole("Student");
        }

        if (jsonObj.getBoolean("hogwartsStaff")) {
            person.setRole("Hogwarts Staff");
        }

        return person;
    }
}
