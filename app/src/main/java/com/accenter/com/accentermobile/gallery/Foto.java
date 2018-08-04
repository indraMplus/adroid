package com.accenter.com.accentermobile.gallery;

import java.io.Serializable;
/**
 * Created by indrablake on 28/02/2018.
 */

public class Foto implements Serializable{
    private String name;
    private String small, medium, large;

    public Foto() {
    }

    public Foto(String name, String small, String medium, String large) {
        this.name = name;
        this.small = small;
        this.medium = medium;
        this.large = large;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
