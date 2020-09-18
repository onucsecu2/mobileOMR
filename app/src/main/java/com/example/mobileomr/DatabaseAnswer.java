package com.example.mobileomr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;
import java.util.List;

public class DatabaseAnswer extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "momr.db";
    public static final String TABLE_NAME = "table_saved_answers";
    public static final String COL1 = "ID";
    public static final String COL2 = "SUBJECT";
    public static final String COL3 = "CODE";
    public static final String COL4 = "NUM_OF_Q";
    public static final String COL5 = "ANS";
    public DatabaseAnswer(Context context) {
        super(context, DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE  TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,SUBJECT STRING NOT NULL, CODE STRING NOT NULL UNIQUE,NUM_OF_Q INTEGER NOT NULL,ANS TEXT NOT NULL)" ;
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean adddata(String subject, String code, int num_q, List<Character> ans)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put(COL2,subject);
        values.put(COL3,code);
        values.put(COL4,num_q);
        values.put(COL5,seriallize(ans));

        long result = db.insert(TABLE_NAME,null,values);

        if(result == -1) {
            return false;
        }
        else return true;
    }

    private String seriallize(List<Character> ans) {
        String str=null;
        for(char ch:ans){
            str=str+ch+',';
        }
        return str;
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return  data;
    }
    public void updateData(String subject, String code,String new_code, int num_q, List<Character> ans){
        SQLiteDatabase db = this.getWritableDatabase();
        String query= "UPDATE "+TABLE_NAME+" SET "+ COL2 + " = '"+subject+ "' ,"+COL3+"= '"+new_code+"' , "+ COL4+" = '"+num_q+"' , "+COL5+"= '"+ seriallize(ans)+"' WHERE  AND COL3 = '"+code+"'";
        db.execSQL(query);

    }
    public void deleteData(String code){
        SQLiteDatabase db = this.getWritableDatabase();
        String query= "DELETE FROM "+TABLE_NAME+" WHERE "+COL3+" = '"+code+"'";
        db.execSQL(query);

    }
}
