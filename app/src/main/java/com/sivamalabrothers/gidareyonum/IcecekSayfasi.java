package com.sivamalabrothers.gidareyonum;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
//
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.InterstitialAd;

public class IcecekSayfasi extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tv;
    RelativeLayout arkaplan;
    private AdView adView;
    LinearLayout reklam_layout,reklam_layout1;
    private static int reklam = 0;
    private static final String REKLAM_ID = "ca-app-pub-3183404528711365/6966286929";
    private InterstitialAd interstitial;
    private static final String REKLAM_ID1 = "ca-app-pub-3183404528711365/6618704894";

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

            setContentView(R.layout.icecek_sayfasi);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        tv = findViewById(R.id.tv);
        arkaplan = findViewById(R.id.giris_layout);
        reklam_layout = findViewById(R.id.reklam_layout);
        reklam_layout1 = findViewById(R.id.reklam_layout1);
        recyclerView = findViewById(R.id.recyclerview_giris);

        IcecekMenuCustomAdapter girisMenuCustomAdapter =
                new IcecekMenuCustomAdapter(this, IcecekMenuItem.getGirisMenuItems());
        recyclerView.setAdapter(girisMenuCustomAdapter);

       /*  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager); */

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);


       reklam_yukle();
       reklam_yukle1();
        animasyonUygula();

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

    }

  /*  private void reklam_yukle1(){
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
*/

    private void reklam_yukle1(){

        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(REKLAM_ID);
        reklam_layout.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);

    }

    private void reklam_yukle(){

        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(REKLAM_ID);
        reklam_layout.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);

    }

    public  void tiklananMenuItem(int position) {

        reklam++;
        String nereden = "icecek_sayfasi";
            if(Build.VERSION.SDK_INT>=21 ){
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(IcecekSayfasi.this);
                Intent krn = new Intent(getApplicationContext(),UrunGoruntule.class);
                krn.putExtra("position",position);
                krn.putExtra("nereden",nereden);
                startActivity(krn,options.toBundle());
            }else {
                Intent krn = new Intent(getApplicationContext(), UrunGoruntule.class);
                krn.putExtra("position", position);
                krn.putExtra("nereden",nereden);
                startActivity(krn);
            }

    }
}
