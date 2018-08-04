package com.accenter.com.accentermobile.data;

/**
 * Created by indrablake on 21/12/2017.
 */
public class EventData {
    private String id;
    private String judulEvent;
    private String isiEvent;
    private String imgEvent;
    private String tglEvent;
    private String jamEvent;
    private String tempatEvent;
    private String postEvent;

    public EventData(){
    }
    public EventData(String id,String judulEvent,String isiEvent,String imgEvent,
                     String tglEvent,String jamEvent,String tempatEvent,String postEvent){
        this.id = id;
        this.judulEvent = judulEvent;
        this.isiEvent = isiEvent;
        this.imgEvent = imgEvent;
        this.tglEvent = tglEvent;
        this.jamEvent = jamEvent;
        this.tempatEvent = tempatEvent;
        this.postEvent = postEvent;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getJudulEvent(){
        return judulEvent;
    }
    public void setJudulEvent(String judulEvent){
        this.judulEvent = judulEvent;
    }
    public String getIsiEvent(){
        return isiEvent;
    }
    public void setIsiEvent(String isiEvent){
        this.isiEvent = isiEvent;
    }
    public String getImgEvent(){
        return imgEvent;
    }
    public void setImgEvent(String imgEvent){
        this.imgEvent = imgEvent;
    }
    public String getTglEvent(){
        return tglEvent;
    }
    public void setTglEvent(String tglEvent){
        this.tglEvent = tglEvent;
    }
    public void setJamEvent(String jamEvent){
        this.jamEvent = jamEvent;
    }
    public String getJamEvent(){
        return jamEvent;
    }
    public String getTempatEvent(){
        return tempatEvent;
    }
    public void setTempatEvent(String tempatEvent){
        this.tempatEvent = tempatEvent;
    }
    public String getPostEvent(){
        return postEvent;
    }
    public void setPostEvent(String postEvent){
        this.postEvent = postEvent;
    }
}
