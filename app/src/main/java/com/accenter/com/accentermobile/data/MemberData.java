package com.accenter.com.accentermobile.data;

/**
 * Created by indrablake on 21/12/2017.
 */
public class MemberData {
     private String id;
     private String nama;
     private String chapter;
     private String nopung;
     private String status;

    public MemberData(){}

    public MemberData(String id, String nama,  String chapter, String nopung, String status) {
        this.id = id;
        this.nama = nama;
        this.chapter = chapter;
        this.nopung = nopung;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaMember() {
        return nama;
    }

    public void setNamaMember(String nama) {
        this.nama = nama;
    }
    public String getNama() {
        return nama;
    }
    public void setNopung(String nopung) {
        this.nopung = nopung;
    }
    public String getNopung() {
        return nopung;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public void setChapter(String chapter) {
        this.chapter = chapter;
    }
    public String getChapter() {
        return chapter;
    }
}
