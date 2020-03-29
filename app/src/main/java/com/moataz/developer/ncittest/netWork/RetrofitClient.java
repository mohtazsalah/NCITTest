package com.moataz.developer.ncittest.netWork;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://alnafizah.com/Khalid_POS.com/V1/test/";
    private static RetrofitClient mInctance;
    private Retrofit retrofit;

    private RetrofitClient (){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if (mInctance == null){
            mInctance = new RetrofitClient();
        }
        return mInctance;
    }

    public APIInterface getApi(){
        return retrofit.create(APIInterface.class);
    }
}
