package com.accenter.com.accentermobile.data;

/**
 * Created by indrablake on 20/12/2017.
 */
public class ArtikelData {
    private String id;
    private String judulArtikel;
    private String isiArtikel;
    private String imgArtikel;
    private String tglArtikel;

    public ArtikelData(){

    }

    public ArtikelData(String id,String judulArtikel,String isiArtikel,String imgArtikel,
                       String tglArtikel){
        this.id = id;
        this.judulArtikel = judulArtikel;
        this.isiArtikel = isiArtikel;
        this.imgArtikel = imgArtikel;
        this.tglArtikel = tglArtikel;

    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getJudulArtikel(){
        return judulArtikel;
    }
    public void setJudulArtikel(String judulArtikel){
        this.judulArtikel = judulArtikel;
    }
    public String getIsiArtikel(){
        return isiArtikel;
    }
    public void setIsiArtikel(String isiArtikel){
        this.isiArtikel = isiArtikel;
    }
    public String getImgArtikel(){
        return imgArtikel;
    }
    public void setImgArtikel(String imgArtikel){
        this.imgArtikel = imgArtikel;
    }
    public String getTglArtikel(){
        return tglArtikel;
    }
    public void setTglArtikel(String tglArtikel){
        this.tglArtikel = tglArtikel;
    }
}
