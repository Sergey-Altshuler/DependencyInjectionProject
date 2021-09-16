package com.Altshuler.exampleScannedClass;

import com.Altshuler.customAnnotation.Inject;
import com.Altshuler.exampleInterface.Movable;

public class VehicleFactory {
    private final Movable movable;

    public Movable getMovable() {
        return movable;
    }

    @Inject
    public VehicleFactory(Movable moveable) {
        this.movable = moveable;
    }
}
