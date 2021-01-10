package com.sivamalabrothers.gidareyonum;


import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import static android.Manifest.permission.CAMERA;

public class BarkodOku extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;
    String sonuc = "";
    String urunAdresi = "";
    String urunDurumu = "";
    String myResult = "";


    private static final int  CUSTOM_DIALOG_ID1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        int currentApiVersion = Build.VERSION.SDK_INT;

        if(currentApiVersion >=  Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                Toast.makeText(getApplicationContext(), "Permission already granted!", Toast.LENGTH_LONG).show();
            }
            else
            {
                requestPermission();
            }
        }
    }

    private boolean checkPermission()
    {
        return (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    @Override
    public void onResume() {
        super.onResume();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if(scannerView == null) {
                    scannerView = new ZXingScannerView(BarkodOku.this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            } else {
                requestPermission();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted){
                        Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and camera", Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA},
                                                            REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(BarkodOku.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void handleResult(Result result) {
        myResult = result.getText();
        VeriTabani vt = new VeriTabani(BarkodOku.this);

        if(!myResult.isEmpty()) {
            Urun  urun = vt.UrunBilgisiAl(myResult);
            //Toast.makeText(BarkodOku.this,urun.getUrunAdi(),Toast.LENGTH_LONG).show();
            if(urun.getUrunAdi().isEmpty()){
                sonuc = "Ürün bulunamadı - Okunan Barkod : "+ myResult;
            }else if(!urun.getUrunAdi().isEmpty()){
                sonuc = urun.getUrunAdi();
                urunAdresi = urun.getUrunAdresi();
                urunDurumu = urun.getKategori();
            }
        }


        Log.d("QRCodeScanner", result.getText());
        Log.d("QRCodeScanner", result.getBarcodeFormat().toString());


        showDialog(CUSTOM_DIALOG_ID1);

    }

    @Override
    protected Dialog onCreateDialog(int id) {

        switch (id){


            case CUSTOM_DIALOG_ID1:

                final Dialog kaydetDialog = new Dialog(this);
                kaydetDialog.setContentView(R.layout.barkod_sonuc);

                final TextView baslik = kaydetDialog.findViewById(R.id.baslik);
                final ImageView image = kaydetDialog.findViewById(R.id.urun_durum_image);

                if(urunDurumu.equals("Helal")){
                    image.setImageResource(R.drawable.yesil);
                }else if(urunDurumu.equals("Haram")){
                    image.setImageResource(R.drawable.red);
                }else if(urunDurumu.equals("Şüpheli")){
                    image.setImageResource(R.drawable.sari);
                }

                final TextView urun_sonuc = kaydetDialog.findViewById(R.id.urun_adi_sonuc);
                urun_sonuc.setText(sonuc);


               final Button goruntule = kaydetDialog.findViewById(R.id.buton_urun_goruntule);
               final Button tamam = kaydetDialog.findViewById(R.id.buton_kapat);


               goruntule.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if(!urunAdresi.isEmpty()) {
                           Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                                   Uri.parse(urunAdresi));
                           startActivity(browserIntent);
                       }
                       else
                           Toast.makeText(BarkodOku.this,"Ürün görüntülenemedi.",Toast.LENGTH_LONG).show();

                       kaydetDialog.cancel();
                   }



               });

                tamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent krn = new Intent(getApplicationContext(), BarkodOku.class);
                        krn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(krn);
                        //scannerView.resumeCameraPreview(BarkodOku.this);
                        kaydetDialog.cancel();
                    }
                });


                return kaydetDialog;
            default:
                return  super.onCreateDialog(id);
        }

    }

}
