package com.kundan.transportdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kundan on 30-03-2017.
 */

public class Fromcentral {

    @SerializedName("car")
    @Expose
    private String car;
    @SerializedName("train")
    @Expose
    private String train;

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

}
