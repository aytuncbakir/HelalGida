package com.sivamalabrothers.gidareyonum;


import java.util.ArrayList;

public class YiyecekMenuItem {

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

    public static ArrayList<YiyecekMenuItem> getGirisMenuItems(){

        ArrayList<YiyecekMenuItem> girisMenuItemArrayList = new ArrayList<YiyecekMenuItem>();



        String [] menuItems = {

                "Bebek Maması",
                "Bisküvi ve Kurabiyeler",
                "Cips, Kraker",
                "Çerez",
                "Çikolota",
                "Dondurma ve Tatlılar",
                "Dondurulmuş Gıda",
                "Et, Balık Ürünleri",
                "Hardal",
                "Hazır Ürünler",
                "Jelibon",
                "Jöle",
                "Kahvaltılıklar",
                "Kefir",
                "Ketçap",
                "Kurutulmuş Meyveler",
                "Krem Şanti",
                "Lokum",
                "Makarna, Şehriye",
                "Marmalet-Reçel",
                "Margarin",
                "Marzipan",
                "Mayonez",
                "Müsli ve Tahıl",
                "Pasta-Kek Malzemeleri,Tadlandırıcılar",
                "Pasta,Yaş Pasta, Kek",
                "Peynir",
                "Sıvıyağ",
                "Sirke",
                "Soslar, Garnitürler ve Meze",
                "Süt Ürünleri",
                "Şekerlemeler,Lolipop,Sakız",
                "Tereyağ",
                "Tuz",
                "Turşular",
                "Unlu Mamülleri, Ekmek",
                "Yemek Malzemeleri ve Baharatlar",
                "Yoğurt ve Pudding",
                "Yumurta"

        };

        for(int i=0; i< menuItems.length; i++){
            YiyecekMenuItem girisMenuItem = new YiyecekMenuItem();
            girisMenuItem.setAdi(menuItems[i]);

            girisMenuItemArrayList.add(girisMenuItem);
        }

        return girisMenuItemArrayList;
    }
}
