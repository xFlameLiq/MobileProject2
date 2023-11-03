package com.example.mobileproject;

import android.os.Parcelable;

import java.io.Serializable;

public class Subjects implements Serializable {

    private int id;
    private String nameSub;
    private String nameTea;

    public Subjects() {
    }

    public Subjects(int id, String nameSub, String nameTea) {
        this.id = id;
        this.nameSub = nameSub;
        this.nameTea = nameTea;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSub() {
        return nameSub;
    }

    public void setNameSub(String nameSub) {
        this.nameSub = nameSub;
    }

    public String getNameTea() {
        return nameTea;
    }

    public void setNameTea(String nameTea) {
        this.nameTea = nameTea;
    }
}
