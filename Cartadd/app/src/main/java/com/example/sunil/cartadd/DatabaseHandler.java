package com.example.sunil.cartadd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Sunil on 11/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //Create Database Version
    public static final int DATABASE_VERSION=1;

    //Define Database Name
    public static final String DATABASE_NAME="User.db";

    //Create Table
    public static final String TABLE_NAME_USER="user_table";
    public static final String TABLE_NAME_PROD="product_table";

    //Create coloumns

    //For Table 1
    public static final String COL_ID ="ID";
    public static final String COL_FULLNAME ="FULLNAME";
    public static final String COL_USERNAME ="NAME";
    public static final String COL_PASS ="PASS";

    //For Table 2
    public static final String COL_PROD_ID="PRODUCT ID";
    public static final  String COL_PROD_NAME="PRODUCT NAME";


    //Here pass 'DATABASE_NAME' instead of 'TABLE_NAME'
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //Create query
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Define query For Table 1
        String CREATE_TABLE_USER="CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_USER +
                "(" +COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_FULLNAME + " TEXT,"
                + COL_USERNAME + " TEXT UNIQUE,"
                + COL_PASS +" TEXT" + ")";

         //Define query For Table 2
         String CREATE_TABLE_PROD="CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_PROD +
                 "(" +COL_PROD_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                 + COL_PROD_NAME +" TEXT UNIQUE" + ")";

         db.execSQL(CREATE_TABLE_USER);
         db.execSQL(CREATE_TABLE_PROD);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_USER);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_PROD);
        onCreate(db);
    }


    //Add data for UserModel For signin
    public boolean addUserData(UserModel umd){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues value=new ContentValues();
        value.put(COL_FULLNAME,umd.getFullname());
        value.put(COL_USERNAME,umd.getUname());
        value.put(COL_PASS,umd.getPass());

        long result=db.insert(TABLE_NAME_USER,null,value);
        if(result == -1)
            return false;
        else
            return true;
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

    public Cursor getAllUserData(){

        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cur=db.rawQuery("select * from "+TABLE_NAME_USER,null);
        return cur;
    }

    public Cursor getAllProductData(){
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cur=db.rawQuery("select * from "+TABLE_NAME_PROD,null);
        return cur;

    }

    public ArrayList<ProductModel> getProductData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = getAllProductData();

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
