package com.example.hp_wiki.helper;

import com.example.hp_wiki.model.House;
import com.example.hp_wiki.model.Spell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PotterAPIJsonParser {
    private static Spell spell;
    private static House house;

    public static Spell createSpellFromJsonString(String spellJsonString, String name) throws JSONException {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(spellJsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i=0;i<jsonArray.length();i++) {
            try {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                if (jsonObj.getString("spell").equals(name)) {
                    setSpell(jsonObj);
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return spell;
    }

    public static House createHouseFromJsonString(String houseJsonString, String name) throws JSONException {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(houseJsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i=0;i<jsonArray.length();i++) {
            try {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                if (jsonObj.getString("name").equals(name)) {
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return house;
    }

    private static void setSpell(JSONObject jsonObj) throws JSONException {
        spell = new Spell();
        spell.setName(jsonObj.getString("spell"));
        if(jsonObj.getString("type") != null){
            spell.setEffect(jsonObj.getString("type"));
        }
        if(jsonObj.getString("effect") != null){
            spell.setType(jsonObj.getString("effect"));
        }
    }
}
