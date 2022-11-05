package com.example.lab3v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn0,btn1,btn2,btn3;
    TextView display,display2,display3;
    EditText editText1,editText2,editText3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display=findViewById(R.id.display);
        display2=findViewById(R.id.display2);
        display3=findViewById(R.id.display3);

        btn0=findViewById(R.id.btn0); // add
        btn1=findViewById(R.id.btn1); // find
        btn2=findViewById(R.id.btn2); // Delete
        btn3=findViewById(R.id.btn3); // view all

        editText1 = findViewById(R.id.editText1); // this is product id
        editText2 = findViewById(R.id.editText2); // this is product name
        editText3 = findViewById(R.id.editText3); // this is product price

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this is add button
                if(editText1.getText().toString().matches("") || editText2.getText().toString().matches("")|| editText3.getText().toString().matches("")){
                    display.setText("Missing Criteria");

                }else{
                    //
                   addID();

                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this is Find button
                if(editText1.getText().toString().matches("") ){
                    display.setText("Missing Criteria for Search by ID");
                    return;
                }

                searchByID();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this is Delete button
                if(editText1.getText().toString().matches("") ){
                    display.setText("Missing Criteria for deletion");

                }else{
                    //
                    deleteProduct();


                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this is View All button
                seeAllProducts();

            }
        });


    }
    public void seeAllProducts(){
        ProductDatabase dbProd = new ProductDatabase(MainActivity.this);
        List<Product> prdList = dbProd.getAllProducts();

        String newDisplay="";
        while (prdList.isEmpty()==false){
            String curr = prdList.remove(0).toString();
            newDisplay+=curr;
            newDisplay+="\n";
        }
        display.setText(newDisplay);


    }

    public void addID(){
        String id = editText1.getText().toString();
        String name = editText2.getText().toString();
        String price = editText3.getText().toString();

        ProductDatabase dbAdmin1 = new ProductDatabase(MainActivity.this);
        Log.d("65","Database made");
        List<Product> productList = dbAdmin1.getAllProducts();

        while (productList.isEmpty()==false){
            String curr = productList.remove(0).getID();
            if(curr.equals(id)){
                display.setText("ID already in use");
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                return;
            }
        }
        boolean go = true;
        if(go==true){
            Product pCurrent = new Product(id,name,price);
            ProductDatabase dbAdmin = new ProductDatabase(MainActivity.this);
            boolean success = dbAdmin.add(pCurrent);
            if(success==true){
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                display.setText("New Product Created: ID: "+id+"; name: "+name+", price: "+price);
            }else{
                //editTextTextID.setText("");
                editText1.setText("");
                display.setText("Product Upload Failed");
            }


        }


    }
    public void deleteProduct(){
        String id = editText1.getText().toString();
        ProductDatabase dbprd = new ProductDatabase(MainActivity.this);
        List<Product> prods = dbprd.getAllProducts();
        while (prods.isEmpty()==false){
            String curr = prods.remove(0).getID();
            if(curr.equals(id)){
                //we have a class to delete
                display.setText("Product Deleted");
                dbprd.deleteProductB(id);
                Log.d("47","154");

            }
            editText1.setText("");
            editText2.setText("");
            editText3.setText("");
            return;
        }
        display.setText("No Product Found to Delete");
    }
    public void searchByID(){
        String id = editText1.getText().toString();
        ProductDatabase dbprd = new ProductDatabase(MainActivity.this);
        Product prods =dbprd.getProduct(id);
        if(prods!=null){
            display.setText("Product Found: "+prods.toString());
        }else{
            display.setText("Product Not Found ");
        }


    }
}