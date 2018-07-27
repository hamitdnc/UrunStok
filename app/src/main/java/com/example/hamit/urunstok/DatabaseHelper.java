package com.example.hamit.urunstok;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    int sayac=0;
    public static final String DATABASE_NAME = "Urunlerim.db";
    public static final String TABLE_NAME = "urunlerim_table";
    public static final int DATABASE_VERSION = 1;
    public static final String COL_1 = "ID";
    public static final String COL_2 = "URUNADI";
    public static final String COL_3 = "TARIH";
    public static final String COL_4 = "MIKTAR";
    public static final String COL_5 = "IRSALIYE";
    public static final String COL_6 = "SAAT";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT ,URUNADI TEXT, TARIH TEXT ,MIKTAR INTEGER ,IRSALIYE TEXT ,SAAT TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String urunadi,String tarih,String miktar,String irsaliye,String saat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,urunadi);
        contentValues.put(COL_3,tarih);
        contentValues.put(COL_4,miktar);
        contentValues.put(COL_5,irsaliye);
        contentValues.put(COL_6,saat);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(String id,String urunadi,String tarih,String miktar,String irsaliye) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,urunadi);
        contentValues.put(COL_3,tarih);
        contentValues.put(COL_4,miktar);
        contentValues.put(COL_5,irsaliye);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public void deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public Cursor getMiktar(String urunAdi) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql_mik = "SELECT MIKTAR FROM " + TABLE_NAME + " WHERE " + COL_2 + " = '" + urunAdi + "';";
        Cursor cursor = db.rawQuery(sql_mik, null);
        return cursor;
    }
    public Cursor butunveriler() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql_mik = "SELECT ID,URUNADI,TARIH,MIKTAR,IRSALIYE FROM urunlerim_table ORDER BY ID ASC ;";
        Cursor cursor = db.rawQuery(sql_mik, null);
        return cursor;
    }
    public Cursor yilveriler() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql_mik = "SELECT ID,URUNADI,TARIH,MIKTAR,IRSALIYE FROM urunlerim_table ORDER BY ID ASC ;";
        Cursor cursor = db.rawQuery(sql_mik, null);
        return cursor;
    }
    public Cursor ayveriler() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql_mik = "SELECT URUNADI,TARIH,MIKTAR FROM urunlerim_table ;";
        Cursor cursor = db.rawQuery(sql_mik, null);
        return cursor;
    }
    public Cursor haftaveriler() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql_mik = "SELECT URUNADI,TARIH,MIKTAR FROM urunlerim_table ;";
        Cursor cursor = db.rawQuery(sql_mik, null);
        return cursor;
    }
    public List<String> VeriListele(){

        List<String> veriler=new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] sutunlar={COL_1,COL_2};
        Cursor cursor=db.query(TABLE_NAME , sutunlar , null , null , null , null, "ID DESC");
        while(cursor.moveToNext()){
            sayac++;
            if(sayac<10)
        veriler.add(cursor.getInt(0) + "-" + cursor.getString(1));

        }
        return veriler;
    }
    public List<String> VeriSpinner(){

        List<String> veriler=new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] sutunlar={COL_1,COL_2};
        Cursor cursor=db.query(TABLE_NAME , sutunlar ,null , null , null , null, "ID DESC");
        while(cursor.moveToNext()){
            sayac++;
            if(sayac<10)
                veriler.add(cursor.getString(1));

        }
        return veriler;
    }
    public void VeriSil(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.close();
    }
}
