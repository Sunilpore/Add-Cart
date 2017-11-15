package com.example.sunil.cartadd;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ListView lv;
    DatabaseHandler db;
    MyAdapter adapter;
    ArrayList<ProductModel> plist;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar ab=getSupportActionBar();
        ab.setLogo(R.drawable.tick);
        ab.setDisplayUseLogoEnabled(true);   //This method will enable your logo
        ab.setDisplayShowHomeEnabled(true);  //This method will enable your home

        mContext=this;
        db=new DatabaseHandler(mContext);
        lv= (ListView) findViewById(R.id.list_view);
       /* alist =new ArrayList<>();

        alist.add(new ProductModel("Poduct 1"));
        alist.add(new ProductModel("Poduct 2"));
        alist.add(new ProductModel("Poduct 3"));
        alist.add(new ProductModel("Poduct 4"));
        alist.add(new ProductModel("Poduct 5"));
        alist.add(new ProductModel("Poduct 6"));
        alist.add(new ProductModel("Poduct 7"));
        alist.add(new ProductModel("Poduct 8"));
        alist.add(new ProductModel("Poduct 9"));
        alist.add(new ProductModel("Poduct 10"));
        MyAdapter adapter=new MyAdapter(this,alist);
        lv.setAdapter(adapter);*/

       db.addProductData(new ProductModel("Poduct 1"));
       db.addProductData(new ProductModel("Poduct 2"));
       db.addProductData(new ProductModel("Poduct 3"));
       db.addProductData(new ProductModel("Poduct 4"));
       db.addProductData(new ProductModel("Poduct 5"));
       db.addProductData(new ProductModel("Poduct 6"));

       plist=db.getProductData();
       adapter=new MyAdapter(mContext,plist);
       lv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case R.id.count_id:
                Toast.makeText(this,"No. of Items are added",Toast.LENGTH_SHORT).show();
                break;

            case R.id.view_id:
                Toast.makeText(this,"Move to next Page",Toast.LENGTH_LONG).show();


                break;

            case R.id.logout_id:

                Intent i=new Intent(HomeActivity.this,MainActivity.class);
                startActivity(i);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
