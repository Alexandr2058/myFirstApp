package com.example.myapplicationdia;

import android.util.Log;

public class Singlton {

    private int id;
    private String name;
    private String secondName;
    private String lastName;
    private String bornData;
    private int inn;
    private String pass;

    private static Singlton instance;
    private Singlton() {}
    public static Singlton getInstance() {
        if (instance == null) {
            instance = new Singlton();
        }
        return instance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBornData() {
        return bornData;
    }

    public void setBornData(String bornData) {
        this.bornData = bornData;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public static void setInstance(Singlton instance) {
        Singlton.instance = instance;
    }


}
