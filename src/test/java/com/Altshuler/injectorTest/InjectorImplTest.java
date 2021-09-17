package com.Altshuler.injectorTest;

import com.Altshuler.classScanner.ClassScanner;
import com.Altshuler.customException.BindingNotFoundException;
import com.Altshuler.customException.ConstructorNotFoundException;
import com.Altshuler.customException.TooManyConstructorsException;
import com.Altshuler.exampleClass.Calculator;
import com.Altshuler.exampleClass.Plane;
import com.Altshuler.exampleClass.Vehicle;
import com.Altshuler.exampleInterface.Countable;
import com.Altshuler.exampleInterface.Flyable;
import com.Altshuler.exampleInterface.Movable;
import com.Altshuler.exampleScannedClass.*;
import com.Altshuler.injector.Injector;
import com.Altshuler.injector.InjectorImpl;
import com.Altshuler.provider.Provider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;


public class InjectorImplTest {
    private final ClassScanner classScanner = new ClassScanner();
    private final Injector injector = new InjectorImpl();

    @BeforeEach
    void initialize() {
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
    void testSuccessBinding() throws InvocationTargetException, InstantiationException, IllegalAccessException, BindingNotFoundException, ConstructorNotFoundException, TooManyConstructorsException {
        injector.bind(Countable.class, Calculator.class);
        Provider daoProvider = injector.getProvider(Countable.class);
        assertNotNull(daoProvider);
        assertNotNull(daoProvider.getInstance());
        assertSame(Calculator.class, daoProvider.getInstance().getClass());
    }

    @Test
    void testNotFoundBinding() {
        Assertions.assertThrows(BindingNotFoundException.class, () ->
                injector.bind(Object.class, String.class));
    }

    @Test
    void testNotFoundConstructor() {
        List<Class> classList = new ArrayList<>();
        classList.add(FailedCalcFactoryWithNoAnnotations.class);
        classScanner.addScannedClasses(classList);
        Assertions.assertThrows(ConstructorNotFoundException.class, () ->
                injector.bind(Countable.class, Calculator.class));
    }

    @Test
    void testTooManyConstructor() {
        List<Class> classList = new ArrayList<>();
        classList.add(FailedCalcFactoryWithTwoAnnotations.class);
        classScanner.addScannedClasses(classList);
        Assertions.assertThrows(TooManyConstructorsException.class, () ->
                injector.bind(Countable.class, Calculator.class));
    }

}
