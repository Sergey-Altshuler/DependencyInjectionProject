package com.Altshuler.customException;

public class ConstructorNotFoundException extends Exception{
    @Override
    public String getMessage() {
        return "Cannot find required constructor";
    }
}
