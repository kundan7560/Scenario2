package com.kundan.transportdemo.restClient;

import java.io.IOException;

import retrofit2.Converter;


public final class ToStringConverter implements Converter<String,String> {
    @Override
    public String convert(String value) throws IOException {
        return null;
    }
}