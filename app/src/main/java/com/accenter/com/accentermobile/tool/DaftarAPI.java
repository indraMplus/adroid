package com.accenter.com.accentermobile.tool;


import com.accenter.com.accentermobile.data.Value;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit2.Call;

/**
 * Created by indrablake on 28/12/2017.
 */
public interface DaftarAPI {
    @FormUrlEncoded
    @POST("/news/pendaftaran.php")
    void insertUser(
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("email") String email,
            @Field("chapter") String chapter,
            @Field("telp") String telp,
            @Field("rangka") String rangka,
            @Field("tahun") String tahun,
            @Field("sim") String sim,
            @Field("keterangan") String ket,
            Callback<Response> callback);
    @FormUrlEncoded
    @POST("edit_member.php")
    Call<Value> ubah(@Field("id")   String id,
                     @Field("nama") String nama,
                     @Field("nopung") String nopung,
                     @Field("region") String region,
                     @Field("status") String status);
}
