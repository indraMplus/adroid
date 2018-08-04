package com.accenter.com.accentermobile.data;

import java.util.List;

/**
 * Created by indrablake on 17/01/2018.
 */
public class Value {
    String value,message;
    List<Result>result;

    public String getValue(){
     return value;
    }
    public String getMessage(){
        return message;
    }
    public List<Result> getResult(){
        return result;
    }
}
