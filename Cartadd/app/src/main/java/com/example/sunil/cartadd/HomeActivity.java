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
    static int runOnce;

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


        /*This method is use when you are not added item to Listview via Database.

        alist =new ArrayList<>();

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

        runOnce=1;

       if(runOnce==0){
           db.addProductData(new ProductModel("Poduct 1",10));
           db.addProductData(new ProductModel("Poduct 2",20));
           db.addProductData(new ProductModel("Poduct 3",20));
           db.addProductData(new ProductModel("Poduct 4",20));
           db.addProductData(new ProductModel("Poduct 5",10));
           db.addProductData(new ProductModel("Poduct 6",10));
           db.addProductData(new ProductModel("Poduct 7",20));
           db.addProductData(new ProductModel("Poduct 8",20));
           db.addProductData(new ProductModel("Poduct 9",30));
           db.addProductData(new ProductModel("Poduct 10",30));
           db.addProductData(new ProductModel("Poduct 11",40));
           db.addProductData(new ProductModel("Poduct 12",50));
           db.addProductData(new ProductModel("Poduct 13",60));
           db.addProductData(new ProductModel("Poduct 14",60));
           db.addProductData(new ProductModel("Poduct 15",60));

           runOnce++;
       }



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
