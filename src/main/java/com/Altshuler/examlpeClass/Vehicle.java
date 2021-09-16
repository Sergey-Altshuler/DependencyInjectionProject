package com.Altshuler.examlpeClass;

import com.Altshuler.exampleInterface.Movable;

public class Vehicle implements Movable {

    @Override
    public void move() {
        System.out.println("I can move");
    }
}
