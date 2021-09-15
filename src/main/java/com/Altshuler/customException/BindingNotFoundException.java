package com.Altshuler.customException;

public class BindingNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Cannot bind classes";
    }
}
