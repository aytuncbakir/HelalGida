package com.sivamalabrothers.gidareyonum;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DosyaOku{

    private Context context;

    public DosyaOku(Context context){
        this.context = context;
    }

    public ArrayList<Urun> dosyadanyukle(String okunacakDosya){
        String dosyaYolu = "data/urun_verileri.txt";
        BufferedReader okumaTamponu = null;
        try{

            AssetManager manager = context.getAssets();
            okumaTamponu = new BufferedReader(
                           new InputStreamReader(manager.open(dosyaYolu), "UTF-8"));

                ArrayList<Urun> urunler = new ArrayList<Urun>();
                String satir = okumaTamponu.readLine();

                while (satir != null) {

                    if (!satir.equals("")) {

                        Urun urun = new Urun();

                        String[] items = satir.split(",");
                        int i = 0;
                        for (String item : items) {
                            if (i == 0) {
                                urun.setUrunAdi(item);
                            } else if (i == 1) {
                                urun.setBarkod(item);
                            } else if (i == 2) {
                                urun.setKategori(item);
                            }else if (i == 3) {
                                urun.setUrunAdresi(item);
                            }
                            i++;
                        }
                        urunler.add(urun);

                    }
                    satir = okumaTamponu.readLine();
                }
            okumaTamponu.close();
            return urunler;


        }catch(FileNotFoundException ex){
           ex.printStackTrace();
        }
        catch(IOException ex1){
            ex1.printStackTrace();
        }finally {
            if (okumaTamponu != null) {
                try {
                    okumaTamponu.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return null;

    }


}
