package com.example.lab3v2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class Product{
    public String ID;
    public String Name;
    public String Price;

    public Product(String id, String name,String price){
        this.ID=id;
        this.Name=name;
        this.Price=price;

    }
    public String getID(){
        return ID;
    }
    public String getName(){
        return Name;
    }
    public String getPrice(){
        return Price;
    }
    public String toString(){
        String out = "ID: "+ID+", Name: "+Name+", Price: "+Price;
        return out;
    }


}

