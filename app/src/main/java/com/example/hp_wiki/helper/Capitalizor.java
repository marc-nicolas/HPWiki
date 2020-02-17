package com.example.hp_wiki.helper;

public class Capitalizor {
    public String capitalizeFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
