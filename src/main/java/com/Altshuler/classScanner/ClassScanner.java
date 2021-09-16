package com.Altshuler.classScanner;

import com.Altshuler.info.InjectionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassScanner {

    public void addInjectedClasses(Map<Class, Class> injected){
        InjectionInfo.setBindingMap(new HashMap<>(injected));
    }

    public void addScannedClasses(List<Class> scanned){
        InjectionInfo.setScannedList(new ArrayList<>(scanned));
    }
}
