package com.example.healthcare;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "CREATE TABLE users (Username TEXT, Email TEXT, password TEXT)";
        sqLiteDatabase.execSQL(qry1);

        String qry2="CREATE TABLE cart (username TEXT, product TEXT, price float,otype TEXT)";
        sqLiteDatabase.execSQL(qry2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void register(String Username, String Email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", Username);
        cv.put("Email", Email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }
    public int login(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {"Username", "password"};
        String selection = "Username = ? AND password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        int result = cursor.getCount();

        cursor.close();
        db.close();

        return result;
    }

    public void addToCart(String username,String product,float price,String otype ){

        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();

    }

    public int cheekCart(String username,String product){
        int result=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]=product;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c= db.rawQuery("select *from cart where username=? AND product=?",str);
        if(c.moveToFirst()){
            result=1;

        }
        db.close();
        return result;
    }

    public void removeCart(String username,String otype) {

        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db=getWritableDatabase();
        db.delete("cart","username=? AND otype=?",str);
        db.close();



    }
    public ArrayList getCartData(String username,String otype){
        ArrayList<String>arr=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String str[]=new String[2];
        str[0]=username;
        str[1]=otype;
        Cursor c=db.rawQuery("select *from cart where username=? AND product=?",str);
        if (c.moveToFirst()){
            do {
                String product=c.getString(1);
                String price=c.getString(2);
                arr.add(product+"$"+price);

            }while (c.moveToNext());

        }
        db.close();
        return arr;
    }



}

