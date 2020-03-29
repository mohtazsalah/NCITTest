package com.moataz.developer.ncittest.netWork;


import com.moataz.developer.ncittest.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @GET("users.php")
    Call<List<User>> getUser();

    @FormUrlEncoded
    @POST("login.php")
    Call<User> Login(
            @Field("user_name") String name,
            @Field("user_pass") String pass
    );

    @FormUrlEncoded
    @POST("signup.php")
    Call<User> sign(
            @Field("user_name") String name2,
            @Field("user_pass") String pass2
    );

}
