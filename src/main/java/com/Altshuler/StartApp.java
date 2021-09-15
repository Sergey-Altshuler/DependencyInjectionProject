package com.Altshuler;

import com.Altshuler.customClass.CustomFirstClass;
import com.Altshuler.customClass.CustomFirstInterface;
import com.Altshuler.injector.Injector;
import com.Altshuler.injector.InjectorImpl;
import com.Altshuler.provider.Provider;

import java.lang.reflect.InvocationTargetException;

public class StartApp {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Injector injector = new InjectorImpl(); //создаем имплементацию инжектора
        injector.bind(CustomFirstInterface.class, CustomFirstClass.class);
        Provider<CustomFirstInterface> provider = injector.getProvider(CustomFirstInterface.class);
        System.out.println(provider);


    }
}
