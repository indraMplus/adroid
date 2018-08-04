package com.accenter.com.accentermobile.data;

/**
 * Created by indrablake on 21/12/2017.
 */
public class KopdarData {
    private String id;
    private String judulKopdar;
    private String isiKopdar;
    private String regionKopdar;
    private String postKopdar;
    private String tglKopdar;

    public KopdarData(){
    }
    public KopdarData(String id,String judulKopdar,String isiKopdar, String tglKopdar,String regionKopdar,String postKopdar){
        this.id = id;
        this.judulKopdar = judulKopdar;
        this.isiKopdar = isiKopdar;
        this.tglKopdar = tglKopdar;
        this.regionKopdar = regionKopdar;
        this.postKopdar = postKopdar;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getJudulKopdar(){
        return judulKopdar;
    }
    public void setJudulKopdar(String judulKopdar){
        this.judulKopdar = judulKopdar;
    }
    public String getIsiKopdar(){
        return isiKopdar;
    }
    public void setIsiKopdar(String isiKopdar){
        this.isiKopdar = isiKopdar;
    }
    public void setTglKopdar(String tglKopdar){
        this.tglKopdar = tglKopdar;
    }
    public String getTglKopdar(){
        return tglKopdar;
    }
    public String getRegionKopdar(){
        return regionKopdar;
    }
    public void setRegionKopdar(String regionKopdar){
        this.regionKopdar = regionKopdar;
    }
    public String getPostKopdar(){
        return postKopdar;
    }
    public void setPostKopdar(String postKopdar){
        this.postKopdar = postKopdar;
    }

}
