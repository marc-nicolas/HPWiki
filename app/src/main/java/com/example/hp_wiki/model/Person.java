package com.example.hp_wiki.model;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Date;

public class Person {
    private String name;
    private String species;
    private String gender;
    private String house;
    private String dateOfBirth;
    private String bloodStatus;
    private String eyeColor;
    private String hairColor;
    private Wand wand;
    private String patronus;
    private String role;
    private String actor;
    private Boolean alive;
    private String image;

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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodStatus() {
        return bloodStatus;
    }

    public void setBloodStatus(String bloodStatus) {
        this.bloodStatus = bloodStatus;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public Wand getWand() {
        return wand;
    }

    public void setWand(Wand wand) {
        this.wand = wand;
    }

    public String getPatronus() {
        return patronus;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public String getRole() {
        return role;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getAlive() {
        if (alive == true) {
            return "This character is still alive";
        }
        return "This character is dead";
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public String getImage() {

        String httpsUrl = image.replaceAll("http:", "https:");
        return httpsUrl;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
