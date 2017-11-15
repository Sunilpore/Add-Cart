package com.example.sunil.cartadd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Sunil on 11/15/2017.
 */

public class DatabasePD extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;

    public static final String DATABASE_NAME="Product.db";

    public static final String TABLE_NAME_PROD="product_table";

    public static final String COL_PROD_ID="PRODUCT ID";
    public static final  String COL_PROD_NAME="PRODUCT NAME";



    public DatabasePD(Context context){
        super(context, TABLE_NAME_PROD, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_PROD="CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_PROD +
                "(" +COL_PROD_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_PROD_NAME +" TEXT UNIQUE" + ")";

        db.execSQL(CREATE_TABLE_PROD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_PROD);
        onCreate(db);
    }

    public boolean addProductData(ProductModel pmd){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues value=new ContentValues();
        value.put(COL_PROD_NAME,pmd.getProdcat());

        long result=db.insert(TABLE_NAME_PROD,null,value);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataProduct(){
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cur=db.rawQuery("select * from "+TABLE_NAME_PROD,null);
        return cur;

    }

    public ArrayList<ProductModel> getProductData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = getAllDataProduct();

        ArrayList<ProductModel> plist =new ArrayList<>();

        if(cur !=null){

            while(cur.moveToNext()){
                String prodname = cur.getString(cur.getColumnIndex(COL_PROD_NAME));

                plist.add(new ProductModel(prodname));
            }
        }
        return plist;
    }
}
