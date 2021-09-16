package com.Altshuler.examlpeClass;

import com.Altshuler.exampleInterface.Flyable;

public class Plane implements Flyable {
    @Override
    public void fly() {
        System.out.println("I can fly");
    }
}
