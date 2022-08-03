package com.example.midexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "products.db";
    private static final String DB_TABLE_NAME = "Product_Table";

    private static final String itemId = "ITEM_ID";
    private static final String itemName = "ITEM_NAME";
    private static final String itemPrice = "ITEM_PRICE";
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE_NAME + "(" + itemId +
            " INTEGER PRIMARY KEY, " + itemName + " TEXT, " + itemPrice + " INTEGER)";


    public DatabaseHelper(Context con){
        super(con, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(int id, String name, int price){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(itemId, id);
        cv.put(itemName, name);
        cv.put(itemPrice, price);

        long res = database.insert(DB_TABLE_NAME, null, cv);

        return res != -1;
    }

    public Cursor viewData(){
        SQLiteDatabase database = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + DB_TABLE_NAME;
        Cursor cur = database.rawQuery(selectAll, null);

        return cur;
    }

    public void deleteData(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + DB_TABLE_NAME + " WHERE " + itemId + " = " + id);
        database.close();
    }

    public void deleteAllData(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + DB_TABLE_NAME);
        database.close();
    }
}
