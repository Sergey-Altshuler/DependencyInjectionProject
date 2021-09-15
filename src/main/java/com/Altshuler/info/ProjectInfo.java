package com.Altshuler.info;

import com.Altshuler.customScanned.CustomClassWithConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectInfo {
    public static final Map<Class, Class> bindingMap = new HashMap<>();
    public static final List<Class> scannedList = List.of(CustomClassWithConstructor.class);
}
