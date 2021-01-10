package com.sivamalabrothers.gidareyonum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class VeriTabani extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "urunler";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLO_URUN = "urun";
    private static final String ROW_ID = "id";
    private static final String ROW_URUN_ADI = "urunAdi";
    private static final String ROW_BARKOD = "barkod";
    private static final String ROW_KATEGORI = "kategori";
    private static final String ROW_URUN_ADRESI = "urunAdresi";


    public VeriTabani(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLO_URUN + "("
                + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_URUN_ADI + " TEXT NOT NULL, "
                + ROW_BARKOD + " TEXT NOT NULL, "
                + ROW_KATEGORI + " TEXT NOT NULL, "
                + ROW_URUN_ADRESI + " TEXT NOT NULL)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void VeriEkle(String urunAdi, String barkod, String kategori,String urunAdresi){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(ROW_URUN_ADI, urunAdi);
            cv.put(ROW_BARKOD, barkod);
            cv.put(ROW_KATEGORI, kategori);
            cv.put(ROW_URUN_ADRESI, urunAdresi);

            db.insert(TABLO_URUN, null,cv);
        }catch (Exception e){
        }
        db.close();
    }


    public Urun UrunBilgisiAl(String barkod){

        Urun  urun = new Urun("","","","");
        String kategori, urunAdi, urunAdresi;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] stunlar = {ROW_ID,ROW_URUN_ADI,ROW_BARKOD,ROW_KATEGORI,ROW_URUN_ADRESI};
            String where = ROW_BARKOD+" = '"+barkod+"'";
            Cursor cursor =  db.query(true, TABLO_URUN, stunlar, where, null, ROW_BARKOD, null, null, null);

            //db.query(TABLO_DUA, stunlar,where,null,null,null,null);

            while (cursor.moveToNext()){

                urunAdi = String.valueOf( cursor.getString(1));
                kategori = String.valueOf( cursor.getString(3));
                urunAdresi = String.valueOf( cursor.getString(4));
                urun.setUrunAdi(urunAdi);
                urun.setBarkod(barkod);
                urun.setKategori(kategori);
                urun.setUrunAdresi(urunAdresi);

            }


        }catch (Exception e){
        }
        db.close();
        return urun;
    }


    public ArrayList<Urun> VeriListele(){
        ArrayList<Urun> veriler = new ArrayList<Urun>();
        String barkod, kategori, urunAdi, urunAdresi;
        Urun urun ;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] stunlar = {ROW_ID,ROW_URUN_ADI,ROW_BARKOD,ROW_KATEGORI,ROW_URUN_ADRESI};
            Cursor cursor = db.query(TABLO_URUN, stunlar,null,null,null,null,null);
            urun = new Urun("","","","");
            while (cursor.moveToNext()){
                urunAdi = String.valueOf( cursor.getString(1));
                barkod = String.valueOf( cursor.getString(2));
                kategori = String.valueOf( cursor.getString(3));
                urunAdresi = String.valueOf( cursor.getString(4));
                urun.setUrunAdi(urunAdi);
                urun.setBarkod(barkod);
                urun.setKategori(kategori);
                urun.setUrunAdresi(urunAdresi);
                veriler.add(urun);
            }
        }catch (Exception e){
        }
        db.close();
        return veriler;
    }

    public int  Idcek(String barkod){
        ArrayList<Urun> veriler = new ArrayList<Urun>();
       int id = -1;
        Urun urun ;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] stunlar = {ROW_ID};
            String where = ROW_BARKOD+" = '"+barkod+"'";
            Cursor cursor = db.query(TABLO_URUN, stunlar,null,null,null,null,null);
            urun = new Urun("","","","");
            while (cursor.moveToNext()){
                id = Integer.valueOf( cursor.getString(0));

            }
        }catch (Exception e){
        }
        db.close();
        return id;
    }


    public ArrayList<Urun> dualariAl(String kategori){
        ArrayList<Urun> dualar = new ArrayList<Urun>();
        Urun dua;
        String id;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] stunlar = {ROW_ID,ROW_KATEGORI};
            String where = ROW_KATEGORI+" = '"+kategori+"'";
            Cursor cursor =  db.query(true, TABLO_URUN, stunlar, where, null, ROW_KATEGORI, null, null, null);

            //db.query(TABLO_DUA, stunlar,where,null,null,null,null);

            while (cursor.moveToNext()){

                id = String.valueOf( cursor.getInt(0));
                dua = new Urun(id,cursor.getString(1), "",""); /// düzenlerken bak
                dualar.add(dua);

            }
        }catch (Exception e){
        }
        db.close();
        return dualar;
    }

    public void VeriSil(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            // id ye göre verimizi siliyoruz
            String where = ROW_ID + " = '" + id+"'" ;
            db.delete(TABLO_URUN,where,null);
        }catch (Exception e){
        }
        db.close();
    }

    public void VeritabaniSil(){
        SQLiteDatabase db = this.getWritableDatabase();
        try {


            db.delete(TABLO_URUN,null,null);
        }catch (Exception e){
        }
        db.close();
    }



    public void VeriDuzenle(int id, String kategori, String arapca, String turkce, String anlam, String adet){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(ROW_KATEGORI, kategori);
            String where = ROW_ID +" = '"+ id + "'";
            db.update(TABLO_URUN,cv,where,null);
        }catch (Exception e){
        }
        db.close();
    }

}