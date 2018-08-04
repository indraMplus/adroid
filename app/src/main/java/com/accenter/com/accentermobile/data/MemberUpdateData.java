package com.accenter.com.accentermobile.data;

/**
 * Created by indrablake on 22/01/2018.
 */
public class MemberUpdateData {
    
    private String id;
    private String nama;
    private String nopung;
    private String chapter;
    private String status;
    private String keterangan;
        public MemberUpdateData(){}

        public MemberUpdateData(String id,String nama,String nopung,String chapter, String status, String keterangan) {
            this.id = id;
            this.nama = nama;
            this.nopung = nopung;
            this.chapter = chapter;
            this.status = status;
            this.keterangan = keterangan;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getChapter() {
            return chapter;
        }

        public void setChapter(String chapter) {
            this.chapter = chapter;
        }

        public String getNopung() {
            return nopung;
        }

        public void setNopung(String nopung) {
            this.nopung = nopung;
        }
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}
