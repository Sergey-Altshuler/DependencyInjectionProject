package com.Altshuler.injectorTest;

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
import com.Altshuler.provider.Provider;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class InjectorImplTest {

    @Before
    public void initialize(){
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
    }
    @Test
    public void testExistingBinding() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Injector injector = new InjectorImpl();
        injector.bind(Countable.class, Calculator.class);
        Provider daoProvider = injector.getProvider(Countable.class);
        assertNotNull(daoProvider);
        assertNotNull(daoProvider.getInstance());
        assertSame(Calculator.class, daoProvider.getInstance().getClass());
    }

}
