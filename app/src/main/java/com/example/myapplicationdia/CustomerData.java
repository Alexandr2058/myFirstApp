package com.example.myapplicationdia;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerData  {

    private int id;
    private String name;
    private String secondName;
    private String lastName;
    private String bornData;
    private int inn;
    private int pass;

    String txtName = "";

    public CustomerData(int id, String lastName, String name, String secondName,  String bornData, int inn, int pass) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.lastName = lastName;
        this.bornData = bornData;
        this.inn = inn;
        this.pass = pass;
    }

    private static CustomerData instance;
    private CustomerData() {

    }
    public static CustomerData getInstance() {
        if (instance == null) {
            instance = new CustomerData();
        }
        return instance;
    }


//    to String
    @Override
    public String toString() {
        return "CustomerData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bornData=" + bornData +
                ", inn=" + inn +
                ", pass=" + pass +
                '}';
    }

    //    getters and setters
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

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }
}
