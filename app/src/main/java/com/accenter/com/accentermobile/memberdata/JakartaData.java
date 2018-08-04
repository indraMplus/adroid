package com.accenter.com.accentermobile.memberdata;

/**
 * Created by indrablake on 23/12/2017.
 */
public class JakartaData {
    private String idj;
    private String namaMemberj;
    private String nopungMemberj;
    private String regionMemberj;
    private String statusMemberj;

    public JakartaData(){}

    public JakartaData(String idj,String namaMemberj,String nopungMemberj,String regionMemberj,String statusMemberj){
        this.idj = idj;
        this.namaMemberj = namaMemberj;
        this.nopungMemberj = nopungMemberj;
        this.statusMemberj = statusMemberj;
        this.regionMemberj = regionMemberj;
    }
    public String getItemId(){
        return idj;
    }
    public void setId(String idj){
        this.idj = idj;
    }
    public String getNamaMember(){
        return namaMemberj;
    }
    public void setNamaMember(String namaMemberj){
        this.namaMemberj = namaMemberj;
    }
    public String getNopungMember(){
        return nopungMemberj;
    }
    public void setNopungMember(String nopungMemberj){
        this.nopungMemberj = nopungMemberj;
    }
    public String getStatusMember(){
        return statusMemberj;
    }
    public void setStatusMember(String statusMemberj){
        this.statusMemberj = statusMemberj;
    }
    public String getRegionMember(){
        return regionMemberj;
    }
    public void setRegionMember(String regionMemberj){
        this.regionMemberj = regionMemberj;
    }
}

