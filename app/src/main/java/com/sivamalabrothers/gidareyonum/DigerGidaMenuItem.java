package com.sivamalabrothers.gidareyonum;


import java.util.ArrayList;

public class DigerGidaMenuItem {

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

    public static ArrayList<DigerGidaMenuItem> getGirisMenuItems(){

        ArrayList<DigerGidaMenuItem> girisMenuItemArrayList = new ArrayList<DigerGidaMenuItem>();

        String [] menuItems = {

                "Diş Macunu",
                "Ecza Ürünleri",
                "İlaç",
                "Jelatin",
                "Kozmetik Ürünleri",
                "Kolonya",
                "Ruj",
                "Saç Bakım Ürünleri",
                "Tablet veya Kapsüllü Ürünler"

        };

        for(int i=0; i< menuItems.length; i++){
            DigerGidaMenuItem girisMenuItem = new DigerGidaMenuItem();
            girisMenuItem.setAdi(menuItems[i]);

            girisMenuItemArrayList.add(girisMenuItem);
        }

        return girisMenuItemArrayList;
    }
}
