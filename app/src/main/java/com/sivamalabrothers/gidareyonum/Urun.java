package com.sivamalabrothers.gidareyonum;



public class Urun {

    private String barkod;
    private String kategori;
    private String urunAdi;
    private String urunAdresi;


    public Urun(){
    }

    public Urun(String urunAdi, String barkod, String kategori, String urunAdresi){

        this.urunAdi = urunAdi;
        this.barkod = barkod;
        this.kategori = kategori;
        this.urunAdresi = urunAdresi;
    }




    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getBarkod() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }


    public String getUrunAdi() {
        return urunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public String getUrunAdresi() {
        return urunAdresi;
    }

    public void setUrunAdresi(String urunAdresi) {
        this.urunAdresi = urunAdresi;
    }
}
