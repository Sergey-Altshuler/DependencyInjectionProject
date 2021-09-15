package com.Altshuler.customScanned;

import com.Altshuler.customAnnotation.Inject;
import com.Altshuler.customClass.CustomFirstInterface;

public class CustomClassWithConstructor {
    private CustomFirstInterface customFirstInterface;

    @Inject
    public CustomClassWithConstructor(CustomFirstInterface customFirstInterface){
        this.customFirstInterface=customFirstInterface;
    }
}
