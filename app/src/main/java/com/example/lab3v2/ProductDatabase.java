package com.example.lab3v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabase extends SQLiteOpenHelper{

    public static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    public static final String ID = "PRODUCTID";
    public static final String NAME = "NAME";
    public static final String PRICE = "PRICE";

    public ProductDatabase(@Nullable Context context) {
        super(context, "Database_Products.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE "+PRODUCT_TABLE+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ID+" TEXT, "+NAME+" TEXT, "+PRICE+" TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean add(Product productIn) {

        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ID,productIn.getID());
        cv.put(NAME,productIn.getName());
        cv.put(PRICE,productIn.getPrice());

        long result = db.insert(PRODUCT_TABLE,  null, cv);

        if(result>0){

            return true;
        }else{

            return false;
        }

    }
    public List<Product> getAllProducts(){
        List<Product> out = new ArrayList<>();
        //create sql query string
        String sqlQuery = "SELECT * FROM "+PRODUCT_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery,null);

        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            int id = cursor.getInt(0);

            String ID = cursor.getString(1);

            String Name = cursor.getString(2);
            String Price = cursor.getString(3);

            cursor.moveToNext();
            Product curr = new Product(ID,Name,Price);
            out.add(curr);
        }
        return out;
    }
    public void deleteProductB(String productID){

        SQLiteDatabase db =  this.getWritableDatabase();
        String deleteFromTableStatement = "DELETE FROM "+PRODUCT_TABLE+" WHERE "+ID+"='"+productID+"'";

        Cursor cursor = db.rawQuery(deleteFromTableStatement,null);
        if(cursor.moveToFirst()){
            Log.d("47","42 DELETE Prod | success ");
        }else{
            Log.d("47","42 DELETE Prod | fail ");
        }






    }
    public Product getProduct(String prodID){
        SQLiteDatabase db =  this.getWritableDatabase();
        String deleteFromTableStatement = "SELECT * FROM "+PRODUCT_TABLE+" WHERE "+ID+"='"+prodID+"'";
        Cursor cursor = db.rawQuery(deleteFromTableStatement,null);

        if(cursor.moveToFirst()){
            String ID = cursor.getString(1);

            String Name = cursor.getString(2);
            String Price = cursor.getString(3);
            return new Product(ID,Name,Price);
        }else{
            return null;
        }


    }


}
