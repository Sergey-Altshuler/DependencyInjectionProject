package com.Altshuler.provider;

public class ProviderImpl<T> implements Provider<T>{
    private Class<T> clazz;

    public ProviderImpl(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public T getInstance() throws InstantiationException, IllegalAccessException {
        T t = clazz.newInstance();
        return t;
    }
}
