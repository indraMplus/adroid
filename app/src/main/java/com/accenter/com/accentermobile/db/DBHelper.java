package com.accenter.com.accentermobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.accenter.com.accentermobile.data.MemberData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indrablake on 17/01/2018.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Adroid.db";
    public static final String TABLE_NAME = "member";
    public static final String IDS = "id";
    public static final String NAMA = "nama";
    public static final String ALAMAT = "alamat";
    public static final String TELP = "telp";
    public static final String EMAIL = "email";
    public static final String REGION = "region";
    public static final String NORANG = "norang";
    public static final String TAHUN = "tahun";
    public static final String NOSIM = "nosim";
    public static final String NOPUNG = "nopung";
    public static final String STATUS = "status";
    public static final String KETERANGAN = "keterangan";
    public static final String JOIN = "joinDate";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT,nama TEXT,alamat TEXT,telp TEXT,email TEXT,region TEXT,norang TEXT,tahun TEXT,nosim TEXT,nopung TEXT,status TEXT,keterangan TEXT,joinDate DATETIME)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String nama,String alamat,String telp,String email,String region,String norang,String tahun,String nosim,String nopung,String keterangan,String joinDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMA,nama);
        contentValues.put(ALAMAT,alamat);
        contentValues.put(TELP, telp);
        contentValues.put(EMAIL,email);
        contentValues.put(REGION,region);
        contentValues.put(NORANG, norang);
        contentValues.put(TAHUN,tahun);
        contentValues.put(NOSIM, nosim);
        contentValues.put(NOPUNG,nopung);
        contentValues.put(KETERANGAN,keterangan);
        contentValues.put(JOIN, joinDate);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        return result != -1;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
    public boolean updateData(String id,String nama,String region,String nopung,String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDS,id);
        contentValues.put(NAMA,nama);
        contentValues.put(REGION, region);
        contentValues.put(NOPUNG,nopung);
        contentValues.put(STATUS, status);
        db.update(TABLE_NAME, contentValues, "id = ?", new String[]{id});
        return true;
    }
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?",new String[] {id});
    }

    public Integer cekMember(MemberData memberData){
        int id = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM member WHERE nama='?'", new String[]{memberData.getId()});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            id = cursor.getInt(0);
            cursor.close();
        }
        return id;
    }
    public List<String> ambilDataMember(){
        List<String> memberDatas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM member",null);
        if(cursor.moveToFirst()){
            do{
                memberDatas.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        //close the cursor
        cursor.close();
        //close the database
        db.close();
        return memberDatas;
    }
}
