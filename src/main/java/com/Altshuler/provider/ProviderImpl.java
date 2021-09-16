package com.Altshuler.provider;

public class ProviderImpl<T> implements Provider<T> {
    private Class<T> clazz;

    public ProviderImpl(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public T getInstance() {
        try {
            T t = clazz.newInstance();
            return t;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
