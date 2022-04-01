package com.example.assignment.insert;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface interfaceSV {
    @FormUrlEncoded
    @POST("insert.php")
    Call<SeverRespone> insert(@Field("hoten") String hoten,
                              @Field("ngaythang") int ngaythang,
                              @Field("diachi") String diachi );
}
