package com.example.myapplicationdia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_PATRONYMIC = "CUSTOMER_PATRONYMIC";
    public static final String CUSTOMER_SURNAME = "CUSTOMER_SURNAME";
    public static final String COLUMN_BORN_DATE = "BORN_DATE";
    public static final String COLUMN_INN = "INN";
    public static final String COLUMN_PASS = "PASS";
    public static final String COLUMN_ID = "ID";

    String nameC = "";
    String sName = "";
    String lName = "";
    String birthDate = "";
    int custInn;

    public DataBaseHelper(@Nullable Context context) {
        super(context, "customerS.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_PATRONYMIC + " TEXT, " + CUSTOMER_SURNAME + " TEXT, " + COLUMN_BORN_DATE + " TEXT, " + COLUMN_INN + " INT, " + COLUMN_PASS + " INT)";
        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne (CustomerData customerData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_NAME, customerData.getName());
        cv.put(COLUMN_CUSTOMER_PATRONYMIC, customerData.getSecondName());
        cv.put(CUSTOMER_SURNAME, customerData.getLastName());
        cv.put(COLUMN_BORN_DATE, customerData.getBornData());
        cv.put(COLUMN_INN, customerData.getInn());
        cv.put(COLUMN_PASS, customerData.getPass());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkPass (String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + CUSTOMER_TABLE + " where pass =?", new String[] {pass});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public String retCustName (String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select " + COLUMN_CUSTOMER_NAME + " from " + CUSTOMER_TABLE + " where pass =?", new String[] {pass});
        if (cursor.moveToFirst())
            nameC = cursor.getString(cursor.getColumnIndexOrThrow("CUSTOMER_NAME"));
//        Log.d("MyLog", "cur = " + nameC);
        return nameC;
    }

    public String retCustSN(String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select " + COLUMN_CUSTOMER_PATRONYMIC + " from " + CUSTOMER_TABLE + " where pass =?", new String[] {password});
        if (cursor.moveToFirst())
            sName = cursor.getString(cursor.getColumnIndexOrThrow("CUSTOMER_PATRONYMIC"));
        return sName;
    }

    public String retCustLN(String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select " + CUSTOMER_SURNAME + " from " + CUSTOMER_TABLE + " where pass =?", new String[] {password});
        if (cursor.moveToFirst())
            lName = cursor.getString(cursor.getColumnIndexOrThrow("CUSTOMER_SURNAME"));
        return lName;
    }

    public String retCustBD(String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select " + COLUMN_BORN_DATE + " from " + CUSTOMER_TABLE + " where pass =?", new String[] {password});
        if (cursor.moveToFirst())
            birthDate = cursor.getString(cursor.getColumnIndexOrThrow("BORN_DATE"));
        return birthDate;
    }

    public int retCustInn(String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select " + COLUMN_INN + " from " + CUSTOMER_TABLE + " where pass =?", new String[] {password});
        if (cursor.moveToFirst())
            custInn = cursor.getInt(cursor.getColumnIndexOrThrow("INN"));
        return custInn;
    }
}
