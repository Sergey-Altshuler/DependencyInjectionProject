package com.Altshuler.exampleScannedClass;

import com.Altshuler.customAnnotation.Inject;
import com.Altshuler.exampleInterface.Countable;

public class CalcFactory {
    private final Countable countable;

    public Countable getCountable() {
        return countable;
    }

    @Inject
    public CalcFactory(Countable countable) {
        this.countable = countable;

    }

}
