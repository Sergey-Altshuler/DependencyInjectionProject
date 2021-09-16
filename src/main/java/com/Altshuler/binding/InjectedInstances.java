package com.Altshuler.binding;

import java.util.HashMap;
import java.util.Map;

public class InjectedInstances { // здесь хранятся объекты классов с автоинжектом
    private static Map<Class, Object> instances = new HashMap<>();

    public static Map<Class, Object> getInstances() {
        return instances;
    }

    public static void addInstance(Class clazz, Object obj) {
        instances.put(clazz, obj);
    }

    public static Object getInstance(Class clazz) {
        return instances.getOrDefault(clazz, null);
    }

}
