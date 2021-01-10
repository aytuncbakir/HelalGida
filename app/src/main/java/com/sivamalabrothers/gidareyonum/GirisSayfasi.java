package com.sivamalabrothers.gidareyonum;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.*;

import java.util.ArrayList;
//
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.InterstitialAd;

public class GirisSayfasi extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    RecyclerView recyclerView;
    TextView tv;
    RelativeLayout arkaplan;
    private AdView adView;
    LinearLayout reklam_layout,reklam_layout1;
    private static int reklam = 0;
    private static final String REKLAM_ID = "ca-app-pub-3183404528711365/6966286929";
    private InterstitialAd interstitial;
    private static final String REKLAM_ID1 = "ca-app-pub-3183404528711365/7518791005";
    private static boolean dataYukle = false;
    Boolean updateEdildiMi ;
    SharedPreferences preferences;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Make to run your application only in portrait mode
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Make to run your application only in LANDSCAPE mode
        //setContentView(R.layout.disable_android_orientation_change);

        setContentView(R.layout.urun_sayfasi);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        tv = findViewById(R.id.tv);
        arkaplan = findViewById(R.id.giris_layout);
        reklam_layout = findViewById(R.id.reklam_layout);
        reklam_layout1 = findViewById(R.id.reklam_layout1);
        recyclerView = findViewById(R.id.recyclerview_giris);

        GirisMenuCustomAdapter girisMenuCustomAdapter =
                new GirisMenuCustomAdapter(this,GirisMenuItem.getGirisMenuItems());
        recyclerView.setAdapter(girisMenuCustomAdapter);

  /*
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

     */
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
            recyclerView.setLayoutManager(gridLayoutManager);


       reklam_yukle();
       reklam_yukle1();
       animasyonUygula();

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        updateEdildiMi = preferences.getBoolean("updateEt",false);



        if(!updateEdildiMi){
            urun_sil();
            urun_yukle();
        }

       //VT_Sil();
       //urun_listele();
       // urunleri_vtden_al();


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        preferences.registerOnSharedPreferenceChangeListener(this);
    }



    private void VT_Sil(){

        VeriTabani vt = new VeriTabani(GirisSayfasi.this);
        for(int i = 1; i<10169;i++)
            vt.VeriSil(i);

    }


    private void urun_listele(){


        DosyaOku dosyaOku = new DosyaOku(GirisSayfasi.this);
        ArrayList<Urun> urunler =  dosyaOku.dosyadanyukle("");
        String data = "";

        for(int i = 0; i< urunler.size(); i++){
            data +=  "\n" +  urunler.get(i).getUrunAdi()+" "+ urunler.get(i).getBarkod()+" "+
                    urunler.get(i).getKategori()+" "+  urunler.get(i).getUrunAdresi()+"\n"+"\n";

        }

        Toast.makeText(GirisSayfasi.this,data,Toast.LENGTH_LONG).show();

    }

    private void urun_sil(){

        VeriTabani vt = new VeriTabani(GirisSayfasi.this);
        vt.VeritabaniSil();

    }

    private void urunleri_vtden_al(){


        VeriTabani vt = new VeriTabani(GirisSayfasi.this);

        ArrayList<Urun> urunler = vt.VeriListele();
        String barkod = urunler.get(urunler.size()-1).getBarkod();
        int id = vt.Idcek(barkod);
        Toast.makeText(GirisSayfasi.this,id+"",Toast.LENGTH_LONG).show();


    }



    private void urun_yukle (){

        DosyaOku dosyaOku = new DosyaOku(GirisSayfasi.this);
        ArrayList<Urun> urunler =  dosyaOku.dosyadanyukle("");

        VeriTabani vt = new VeriTabani(GirisSayfasi.this);

        for(int i = 0; i< urunler.size(); i++){
            vt.VeriEkle( urunler.get(i).getUrunAdi(), urunler.get(i).getBarkod(),
                    urunler.get(i).getKategori(), urunler.get(i).getUrunAdresi());
        }


        updateEdildiMi = true;

    }

    private void animasyonUygula(){
        if(Build.VERSION.SDK_INT >=21){
            Slide enterTransition = new Slide();
            enterTransition.setDuration(300);
            enterTransition.setSlideEdge(Gravity.RIGHT);
            getWindow().setEnterTransition(enterTransition);
        }


    }

    // geri butonuna basıldığında çalışır
    @Override
    public boolean onSupportNavigateUp(){
        if(Build.VERSION.SDK_INT >= 21)
            finishAfterTransition();
        else
            finish();
        return true;
    }


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("updateEt",updateEdildiMi);
        editor.commit();

    }

   private void reklam_yukle_gecis(){
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(REKLAM_ID1);

        AdRequest adRequest = new AdRequest.Builder().build();

        interstitial.loadAd(adRequest);
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (interstitial.isLoaded()) {
                    interstitial.show();
                }
            }
        });


      }

    private void reklam_yukle(){

        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(REKLAM_ID);
        reklam_layout.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);

    }

    private void reklam_yukle1(){

        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(REKLAM_ID1);
        reklam_layout1.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);




    }

    public  void tiklananMenuItem(int position) {

        reklam++;

        if(position == 0) {

            if (Build.VERSION.SDK_INT >= 21) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(GirisSayfasi.this);
                Intent krn = new Intent(getApplicationContext(), YiyecekSayfasi.class);
                krn.putExtra("position", position);
                startActivity(krn, options.toBundle());
            } else {
                Intent krn = new Intent(getApplicationContext(), YiyecekSayfasi.class);
                krn.putExtra("position", position);
                startActivity(krn);
            }
        }else if(position == 1) {

            if (Build.VERSION.SDK_INT >= 21) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(GirisSayfasi.this);
                Intent krn = new Intent(getApplicationContext(), IcecekSayfasi.class);
                krn.putExtra("position", position);
                startActivity(krn, options.toBundle());
            } else {
                Intent krn = new Intent(getApplicationContext(), IcecekSayfasi.class);
                krn.putExtra("position", position);
                startActivity(krn);
            }
        }else if(position == 2) {

            if (Build.VERSION.SDK_INT >= 21) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(GirisSayfasi.this);
                Intent krn = new Intent(getApplicationContext(), DigerGidaSayfasi.class);
                krn.putExtra("position", position);
                startActivity(krn, options.toBundle());
            } else {
                Intent krn = new Intent(getApplicationContext(), DigerGidaSayfasi.class);
                krn.putExtra("position", position);
                startActivity(krn);
            }
        }else if(position == 3) {

            if (Build.VERSION.SDK_INT >= 21) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(GirisSayfasi.this);
                Intent krn = new Intent(getApplicationContext(), EKodSayfasi.class);
                krn.putExtra("position", position);
                startActivity(krn, options.toBundle());
            } else {
                Intent krn = new Intent(getApplicationContext(), EKodSayfasi.class);
                krn.putExtra("position", position);
                startActivity(krn);
            }
        }else if(position == 4) {
            String nereden = "giris_sayfasi";

            if (Build.VERSION.SDK_INT >= 21) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(GirisSayfasi.this);
                Intent krn = new Intent(getApplicationContext(), UrunGoruntule.class);
                krn.putExtra("nereden",nereden);
                krn.putExtra("position", position);
                startActivity(krn, options.toBundle());
            } else {
                Intent krn = new Intent(getApplicationContext(), UrunGoruntule.class);
                krn.putExtra("position", position);
                krn.putExtra("nereden",nereden);
                startActivity(krn);
            }
        }else if(position == 5) {

            if (Build.VERSION.SDK_INT >= 21) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(GirisSayfasi.this);
                Intent krn = new Intent(getApplicationContext(), BarkodOku.class);
                startActivity(krn, options.toBundle());
            } else {
                Intent krn = new Intent(getApplicationContext(), BarkodOku.class);
                startActivity(krn);
            }
        }


    }
}
