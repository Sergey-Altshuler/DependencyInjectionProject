package com.Altshuler.launch;

import com.Altshuler.classScanner.ClassScanner;
import com.Altshuler.examlpeClass.Calculator;
import com.Altshuler.examlpeClass.Plane;
import com.Altshuler.examlpeClass.Vehicle;
import com.Altshuler.exampleInterface.Countable;
import com.Altshuler.exampleInterface.Flyable;
import com.Altshuler.exampleInterface.Movable;
import com.Altshuler.exampleScannedClass.CalcFactory;
import com.Altshuler.exampleScannedClass.PlaneFactory;
import com.Altshuler.exampleScannedClass.VehicleFactory;
import com.Altshuler.info.InjectionInfo;
import com.Altshuler.injector.Injector;
import com.Altshuler.injector.InjectorImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InjInitializer {

    public void inject(){
        ClassScanner classScanner = new ClassScanner();
        Map<Class, Class> classMap = new HashMap<>(); // здесь тестовые классы и интерфейсы
        classMap.put(Countable.class, Calculator.class);
        classMap.put(Flyable.class, Plane.class);
        classMap.put(Movable.class, Vehicle.class);
        classScanner.addInjectedClasses(classMap);
        List<Class> classList = new ArrayList<>();
        classList.add(CalcFactory.class);
        classList.add(PlaneFactory.class);
        classList.add(VehicleFactory.class);
        classScanner.addScannedClasses(classList);
        injectConstructors();
    }
    private void injectConstructors(){ // метод инжектит конструкторы
        Injector injector = new InjectorImpl();
        for (Map.Entry<Class, Class> pair: InjectionInfo.getBindingMap().entrySet())
        {
            try {
                Class intf = pair.getKey();
                Class impl = pair.getValue();
                injector.bind(intf, impl);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
