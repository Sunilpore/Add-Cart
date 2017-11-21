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

import java.util.ArrayList;

public class CartView extends AppCompatActivity {

    DatabaseHandler db;
    ListView cartlv;
    Context mContext;
    CartAdapter ctadapter;
    ArrayList<CartModel> cartlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartview);

        cartlv= (ListView) findViewById(R.id.cartlist_view);

        mContext=this;
        db=new DatabaseHandler(mContext);


        cartlist=db.getCartData();
        ctadapter=new CartAdapter(mContext,cartlist);
        cartlv.setAdapter(ctadapter);

        ActionBar ab=getSupportActionBar();
        ab.setLogo(R.drawable.tick);
        ab.setDisplayUseLogoEnabled(true);   //This method will enable your logo
        ab.setDisplayShowHomeEnabled(true);  //This method will enable your home

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.order_activity,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case R.id.home_view_id:
                Intent i=new Intent(CartView.this,HomeActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.logout_id2:
                Intent i1=new Intent(CartView.this,MainActivity.class);
                startActivity(i1);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
