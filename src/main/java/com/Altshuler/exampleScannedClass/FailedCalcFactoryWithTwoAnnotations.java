package com.Altshuler.exampleScannedClass;

import com.Altshuler.customAnnotation.Inject;
import com.Altshuler.exampleInterface.Countable;

public class FailedCalcFactoryWithTwoAnnotations {
    private final Countable countable;

    @Inject
    public FailedCalcFactoryWithTwoAnnotations(Countable countable) {
        this.countable = countable;
    }

    @Inject
    public FailedCalcFactoryWithTwoAnnotations(Countable countable, Integer number) {
        this.countable = countable;
    }

    public Countable getCountable() {
        return countable;
    }
}
