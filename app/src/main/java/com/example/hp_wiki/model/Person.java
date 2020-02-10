package com.example.hp_wiki.model;

import androidx.annotation.NonNull;

import java.util.Date;

public class Person  {
    private String name;
    private String alias;
    private String species;
    private String gender;
    private String school;
    private String house;
    private String dateOfBirth;
    private String bloodStatus;
    private String eyeColor;
    private String hairColor;
    private Wand wand;
    private String patronus;
    private String boggart;
    private String role;
    private String actor;
    private Boolean alive;
    private String image;
    private Boolean ministryOfMagic;
    private Boolean orderOfThePhoenix;
    private Boolean dumbledoresArmy;
    private Boolean deathEater;

    public Person(String name, String species, String bloodStatus, Boolean ministryOfMagic, Boolean orderOfThePhoenix, Boolean dumbledoresArmy, Boolean deathEater) {
        this.name = name;
        this.species = species;
        this.bloodStatus = bloodStatus;
        this.ministryOfMagic = ministryOfMagic;
        this.orderOfThePhoenix = orderOfThePhoenix;
        this.dumbledoresArmy = dumbledoresArmy;
        this.deathEater = deathEater;
    }

    public Person(String name, String species, String gender, String house, String dateOfBirth, String bloodStatus, String eyeColor, String hairColor, Wand wand, String patronus, String actor, Boolean alive, String image) {
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.house = house;
        this.dateOfBirth = dateOfBirth;
        this.bloodStatus = bloodStatus;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.wand = wand;
        this.patronus = patronus;
        this.actor = actor;
        this.alive = alive;
        this.image = image;
    }

    public Person() {

    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getBloodStatus() {
        return bloodStatus;
    }

    public void setBloodStatus(String bloodStatus) {
        this.bloodStatus = bloodStatus;
    }
}
