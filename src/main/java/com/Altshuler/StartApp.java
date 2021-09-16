package com.Altshuler;

import com.Altshuler.launch.InjInitializer;

public class StartApp {
    public static void main(String[] args) {
        new InjInitializer().inject(); // приложение автоматически создает объекты классов с inject-annotated конструкторами и инжектит поля
    }
}
