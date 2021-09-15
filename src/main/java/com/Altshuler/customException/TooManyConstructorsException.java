package com.Altshuler.customException;

public class TooManyConstructorsException extends Exception{
    @Override
    public String getMessage() {
        return "Too many constructors";
    }
}
