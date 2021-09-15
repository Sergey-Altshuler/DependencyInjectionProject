package com.Altshuler.injector;

import com.Altshuler.provider.Provider;

import java.lang.reflect.InvocationTargetException;

public interface Injector {

    <T> Provider<T> getProvider(Class<T> type);

    <T> void bind(Class<T> intf, Class<? extends T> impl) throws InstantiationException, IllegalAccessException, InvocationTargetException;

}
