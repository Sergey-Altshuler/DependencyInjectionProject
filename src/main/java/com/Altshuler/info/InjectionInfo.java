package com.Altshuler.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InjectionInfo {

    private static Map<Class, Class> bindingMap = new HashMap<>();
    private static List<Class> scannedList = new ArrayList<>();

    public static Map<Class, Class> getBindingMap() {
        return bindingMap;
    }

    public static List<Class> getScannedList() {
        return scannedList;
    }

    public static void setBindingMap(Map<Class, Class> bindingMap) {
        InjectionInfo.bindingMap = bindingMap;
    }

    public static void setScannedList(List<Class> scannedList) {
        InjectionInfo.scannedList = scannedList;
    }
}
