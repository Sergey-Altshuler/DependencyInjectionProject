package com.Altshuler.exampleScannedClass;

import com.Altshuler.exampleInterface.Countable;

public class FailedCalcFactoryWithNoAnnotations {
    private final Countable countable;

    public FailedCalcFactoryWithNoAnnotations(Countable countable) {
        this.countable = countable;
    }

    public Countable getCountable() {
        return countable;
    }

}
