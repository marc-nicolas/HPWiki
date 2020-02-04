package com.example.hp_wiki.dal;

import com.example.hp_wiki.model.Person;
import com.example.hp_wiki.model.Wand;

import java.util.ArrayList;
import java.util.List;

public class PersonDao {
    public static List<Person> getAll() {
        List<Person> availablePersons = new ArrayList<>();
        availablePersons.add(new Person("wqd", "12e12", "WSFD", "SDFEG", "erg", "4h5", "erng", "wqegqweg", new Wand("f2", "wqef", 1), "reg", "hugi", true, "wfe"));
        return availablePersons;
    }
}
