package com.Altshuler.provider;

public interface Provider<T>{
    T getInstance() throws InstantiationException, IllegalAccessException;
}
