package com.accenter.com.accentermobile.data;

/**
 * Created by indrablake on 12/04/2018.
 */

public class UserRoom {
    public String email;
    public String username;
    public String password;
    public String avatar;
    public Status status;
    public Pesan pesan;

    public UserRoom(){
        pesan = new Pesan();
        status.isOnline = false;
        status.timestamp = 0;
        pesan.idUserChatroom = "0";
        pesan.text = "";
        pesan.timestamp = 0;

    }
}
