package com.sivamalabrothers.gidareyonum;


import java.util.ArrayList;

public class GirisMenuItem {

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

    public static ArrayList<GirisMenuItem> getGirisMenuItems(){

        ArrayList<GirisMenuItem> girisMenuItemArrayList = new ArrayList<GirisMenuItem>();
        int[] resimler={
                R.drawable.yiyecekler,
                R.drawable.icecekler,
                R.drawable.diger,
                R.drawable.ekod,
                R.drawable.info,
                R.drawable.barkod

        };

        String [] menuItems = {

                "YİYECEKLER",
                "İÇECEKLER",
                "DİĞER",
                "E-KODLARI",
                "BİLGİLENDİRME",
                "BARKOD OKUT"

        };

        for(int i=0; i< resimler.length; i++){
            GirisMenuItem girisMenuItem = new GirisMenuItem();
            girisMenuItem.setAdi(menuItems[i]);
            girisMenuItem.setImgId(resimler[i]);

            girisMenuItemArrayList.add(girisMenuItem);
        }

        return girisMenuItemArrayList;
    }
}
