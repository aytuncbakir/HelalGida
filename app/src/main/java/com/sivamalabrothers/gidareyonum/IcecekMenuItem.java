package com.sivamalabrothers.gidareyonum;


import java.util.ArrayList;

public class IcecekMenuItem {

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

    public static ArrayList<IcecekMenuItem> getGirisMenuItems(){

        ArrayList<IcecekMenuItem> girisMenuItemArrayList = new ArrayList<IcecekMenuItem>();


        String [] menuItems = {

                "Meyve ve Sebze Suları",
                "Enerji İçecekleri",
                "Asitli İçecekler",
                "Sütlü İçecekler",
                "İce Tea ve Çay ve Şurup",
                "Şurup ve Toz İçecekler",
                "Diğer İçecekler",
                "Aromalı İçecekler",
                "Kahve ve Kahve Tozları"
   };

        for(int i=0; i< menuItems.length; i++){
            IcecekMenuItem girisMenuItem = new IcecekMenuItem();
            girisMenuItem.setAdi(menuItems[i]);

            girisMenuItemArrayList.add(girisMenuItem);
        }

        return girisMenuItemArrayList;
    }
}
