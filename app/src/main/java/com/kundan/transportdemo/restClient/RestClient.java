package com.kundan.transportdemo.restClient;


import com.kundan.transportdemo.interfaces.CustomApiInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static CustomApiInterface customApiInterface;
    private static String baseUrl = "http://express-it.optusnet.com.au";

    public static CustomApiInterface getClient() {
        if (customApiInterface == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okClient)
                    .build();
            customApiInterface = client.create(CustomApiInterface.class);
        }
        return customApiInterface;
    }


}
