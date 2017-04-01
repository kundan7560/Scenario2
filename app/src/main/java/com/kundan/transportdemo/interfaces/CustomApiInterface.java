package com.kundan.transportdemo.interfaces;


import com.kundan.transportdemo.model.TransportModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.POST;


public interface CustomApiInterface {
    @POST("/sample.json")
    Call<ArrayList<TransportModel>> getTransportList();

}


