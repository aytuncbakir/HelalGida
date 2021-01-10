package com.sivamalabrothers.gidareyonum;


import java.util.ArrayList;

public class EKodMenuItem {

    private String adi;
    private int imgId;

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public static ArrayList<EKodMenuItem> getGirisMenuItems(){

        ArrayList<EKodMenuItem> girisMenuItemArrayList = new ArrayList<EKodMenuItem>();
        int[] resimler={
                R.drawable.ekod,
                R.drawable.eharam,
                R.drawable.esupheli,
                R.drawable.ehelal,
                R.drawable.sagliga_zararli,
                R.drawable.haram_katki

        };

        String [] menuItems = {

                "TÜM E-KODLARI",
                "HARAM E-KODLARI",
                "ŞÜPHELİ E-KODLARI",
                "HELAL E-KODLARI",
                "SAĞLIĞA ZARARLI E-KODLARI",
                "HARAM KATKI MADDELERİ",

        };

        for(int i=0; i< resimler.length; i++){
            EKodMenuItem girisMenuItem = new EKodMenuItem();
            girisMenuItem.setAdi(menuItems[i]);
            girisMenuItem.setImgId(resimler[i]);

            girisMenuItemArrayList.add(girisMenuItem);
        }

        return girisMenuItemArrayList;
    }
}
