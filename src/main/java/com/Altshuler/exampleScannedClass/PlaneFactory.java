package com.Altshuler.exampleScannedClass;

import com.Altshuler.customAnnotation.Inject;
import com.Altshuler.exampleInterface.Flyable;

public class PlaneFactory {
    private final Flyable flyable;

    public Flyable getFlyable() {
        return flyable;
    }

    @Inject
    public PlaneFactory(Flyable flyable) {
        this.flyable = flyable;
    }
}
