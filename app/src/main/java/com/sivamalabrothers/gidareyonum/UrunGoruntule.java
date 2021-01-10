package com.sivamalabrothers.gidareyonum;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Locale;


public class UrunGoruntule extends AppCompatActivity {

    private WebView webview;
    private Context context = this;
    private Bundle gelenData;
    private String gelenString;
    private int position;
    String nereden_geldin = "";
    String dialog_ekod_bilgisi ="";


    // Justify tag
    String justifyTag = "<html><body style='text-align:justify;'>%s</body></html>";

    LinearLayout reklam_layout;
    private static int reklam = 0;
    private static final String REKLAM_ID = "ca-app-pub-3183404528711365/9801646900";
    private InterstitialAd interstitial;
    private static final String REKLAM_ID1 = "ca-app-pub-3183404528711365/9801646900";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.urun_goruntule);

        initViews();


    }

    private void initViews(){

        gelenData = getIntent().getExtras();
        if(gelenData != null) {
            nereden_geldin = gelenData.getString("nereden");
            position = gelenData.getInt("position");

        }


        webview = findViewById(R.id.webview);



            reklam_yukle1();

        loadData ();

        Toast.makeText(context,"Güncellemeler ile eksik datalar tamamlanacaktır.",Toast.LENGTH_LONG).show();


    }


       private void reklam_yukle1(){
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
/*
    private void reklam_yukle(){

        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(REKLAM_ID);
        reklam_layout.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);

    }
*/


    private void loadData (){

        gelenString = "";
        dialog_ekod_bilgisi = "";
        if(nereden_geldin.equals("yiyecek_sayfasi")){
            dialog_ekod_bilgisi = context.getResources().getString(R.string.isaretlerin_anlami_ekodlari);
            if (position == 0) {
                gelenString = context.getResources().getString(R.string.bebek_mamasi_ekodlari);
            } else if (position == 1) {
                gelenString = context.getResources().getString(R.string.biskuvi_ekodlari);

            } else if (position == 2) {
                gelenString = context.getResources().getString(R.string.cips_ekodlari);

            } else if (position == 3) {
                gelenString = context.getResources().getString(R.string.cerez_ekodlari);

            } else if (position == 4) {
                gelenString = context.getResources().getString(R.string.cikolata_sekerleme_draje_ekodlari);

            } else if (position == 5) {
                gelenString = context.getResources().getString(R.string.dondurma_ekodlari);

            } else if (position == 6) {
                gelenString = context.getResources().getString(R.string.dondurulmus_gida_ekodlari);

            } else if (position == 7) {
                gelenString = context.getResources().getString(R.string.et_urunleri_ekodlari);

            } else if (position == 8) {
                gelenString = context.getResources().getString(R.string.hardal_ekodlari);

            } else if (position == 9) {
                gelenString = context.getResources().getString(R.string.hazir_corba_ekodlari);

            } else if (position == 10) {
                gelenString = context.getResources().getString(R.string.cikolata_sekerleme_draje_ekodlari);

            } else if (position == 11) {
                gelenString = context.getResources().getString(R.string.jole_ekodlari);

            } else if (position == 12) {
                gelenString = context.getResources().getString(R.string.peynir_ekodlari);
                // kahvaltılıklar

            } else if (position == 13) {
                gelenString = context.getResources().getString(R.string.aromali_sutlu_icecekler_ekodlari);

            } else if (position == 14) {
                gelenString = context.getResources().getString(R.string.ketcap_ekodlari);

            } else if (position == 15) {
                gelenString = context.getResources().getString(R.string.kurutulmus_meyve_ekodlari);

            } else if (position == 16) {
                gelenString = context.getResources().getString(R.string.krem_santi_ekodlari);

            } else if (position == 17) {
                gelenString = context.getResources().getString(R.string.lokum_ekodlari);

            } else if (position == 18) {
                gelenString = context.getResources().getString(R.string.sehriye_ekodlari);

            } else if (position == 19) {
                gelenString = context.getResources().getString(R.string.marmelat_ekodlari);

            }else if (position == 20) {
                gelenString = context.getResources().getString(R.string.margarin_ekodlari);

            }else if(position == 21){
                gelenString = context.getResources().getString(R.string.marzipan_ekodlari);

            }else if(position == 22){
                gelenString = context.getResources().getString(R.string.mayonez_ekodlari);

            }else if(position == 23){
                gelenString = context.getResources().getString(R.string.misir_gevregi_ekodlari);
                // musli ve tahıllarda e kodları

            }else if(position == 24){
                gelenString = context.getResources().getString(R.string.pasta_ekodlari);

            }else if(position == 25){
                gelenString = context.getResources().getString(R.string.pasta_ekodlari);

            }else if(position == 26){
                gelenString = context.getResources().getString(R.string.peynir_ekodlari);

            }else if(position == 27){
                gelenString = context.getResources().getString(R.string.siviyag_ekodlari);

            }else if(position == 28){
                gelenString = context.getResources().getString(R.string.soslar_ekodlari);
                gelenString += context.getResources().getString(R.string.konserve_bezelye_ekodlari);
                // konserve sebze e kodları

            }else if(position == 29){
                gelenString = context.getResources().getString(R.string.aromali_sutlu_icecekler_ekodlari);

            }else if (position == 30) {
                gelenString = context.getResources().getString(R.string.cikolata_sekerleme_draje_ekodlari);
                gelenString += context.getResources().getString(R.string.sakiz_ekodlari);
                // sakız e kodları

            }else if (position == 31) {
                gelenString = context.getResources().getString(R.string.tereyag_ekodlari);

            } else if (position == 32) {
                gelenString = context.getResources().getString(R.string.tuz_ekodlari);

            } else if (position == 33) {
                gelenString = context.getResources().getString(R.string.tursu_ekodlari);

            } else if (position == 34) {
                gelenString = context.getResources().getString(R.string.ekmek_unlu_gida_ekodlari);

            } else if (position == 35) {
                gelenString = context.getResources().getString(R.string.cesni_ekodlari);
                // yemek malzemeleri ve baharatlar

            } else if (position == 36) {
                gelenString = context.getResources().getString(R.string.yogurt_ekodlari);
                gelenString += context.getResources().getString(R.string.puding_ekodlari);
                //pudding eklencek

            } else if (position == 37) {
                gelenString = context.getResources().getString(R.string.yumurta);

            } else if (position == 38) {
                gelenString = context.getResources().getString(R.string.xxx);

            }

        }else if(nereden_geldin.equals("icecek_sayfasi")) {
            dialog_ekod_bilgisi = context.getResources().getString(R.string.isaretlerin_anlami_ekodlari);
            if (position == 0) {
                gelenString = context.getResources().getString(R.string.meyve_suyu_ekodlari);
                gelenString += context.getResources().getString(R.string.sebze_konservesi_ekodlari);

            } else if (position == 1) {
                gelenString = context.getResources().getString(R.string.icecek_ekodlari);

            } else if (position == 2) {
                gelenString = context.getResources().getString(R.string.gazoz_kola_renkli_icecek_ekodlari);

            } else if (position == 3) {
                gelenString = context.getResources().getString(R.string.aromali_sutlu_icecekler_ekodlari);

            } else if (position == 4) {
                gelenString = context.getResources().getString(R.string.icecek_ekodlari);

            } else if (position == 5) {
                gelenString = context.getResources().getString(R.string.surup_ekodlari);

            } else if (position == 6) {
                gelenString = context.getResources().getString(R.string.icecek_ekodlari);

            } else if (position == 7) {
                gelenString = context.getResources().getString(R.string.icecek_ekodlari);

            } else if (position == 8) {
                gelenString = context.getResources().getString(R.string.kahve_kreması_ekodlari);

            }

        }else if(nereden_geldin.equals("ekod_sayfasi")) {
            if (position == 5) {
                gelenString = context.getResources().getString(R.string.haram_maddeler_tum_diller);

            } else  {
                gelenString = context.getResources().getString(R.string.tum_ekodlari);

            }

        }else if(nereden_geldin.equals("diger_gida_sayfasi")) {
            dialog_ekod_bilgisi = context.getResources().getString(R.string.isaretlerin_anlami_ekodlari);
            if (position == 0) {
                gelenString = context.getResources().getString(R.string.dis_macunu_ekodlari);

            } else if (position == 1) {
                gelenString = context.getResources().getString(R.string.ecza_urunleri_ekodlari);

            } else if (position == 2) {
                gelenString = context.getResources().getString(R.string.ilac_ekodlari);

            } else if (position == 3) {
                gelenString = context.getResources().getString(R.string.jelatin_ekodlari);

            } else if (position == 4) {
                gelenString = context.getResources().getString(R.string.kozmetik_ekodlari);

            } else if (position == 5) {
                gelenString = context.getResources().getString(R.string.kolonya_ekodlari);

            } else if (position == 6) {
                gelenString = context.getResources().getString(R.string.ruj_ekodlari);

            } else if (position == 7) {
                gelenString = context.getResources().getString(R.string.sac_bakim_ekodlari);
                gelenString += context.getResources().getString(R.string.sampuan_ekodlari);
            } else if (position == 8) {
                gelenString = context.getResources().getString(R.string.tablet_kapsullu_urunler_ekodlari);

            }

        }else if(nereden_geldin.equals("giris_sayfasi")) {
            gelenString = context.getResources().getString(R.string.bilgilendirme);
        }

        gelenString = dialog_ekod_bilgisi + gelenString;

        gelenString = String.format(Locale.US, justifyTag, gelenString);
        // Load the data in the web view
        webview.loadDataWithBaseURL("", gelenString, "text/html", "UTF-8", "");




    }




}