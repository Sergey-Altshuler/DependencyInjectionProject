package com.Altshuler.injector;

import com.Altshuler.binding.InjectedInstances;
import com.Altshuler.customException.BindingNotFoundException;
import com.Altshuler.customException.ConstructorNotFoundException;
import com.Altshuler.customException.TooManyConstructorsException;
import com.Altshuler.info.InjectionInfo;
import com.Altshuler.provider.Provider;
import com.Altshuler.provider.ProviderImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class InjectorImpl implements Injector {

    @Override
    public <T> Provider<T> getProvider(Class<T> type) {
        if (!InjectionInfo.getBindingMap().containsKey(type))
            return null;
        else {
            Class clazz = InjectionInfo.getBindingMap().get(type);
            return new ProviderImpl<>(clazz);
        }
    }

    @Override
    public <T> void bind(Class<T> intf, Class<? extends T> impl) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        if ((getProvider(intf) == null) || (!getProvider(intf).getInstance().getClass().equals(impl))) {
            try {
                throw new BindingNotFoundException();
            } catch (BindingNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
        List<Class> classList = InjectionInfo.getScannedList();
        for (Class clazz : classList) {
            Constructor[] constructors = clazz.getConstructors();
            int countOfAnnotatedConstructors = 0;

            for (Constructor constructor : constructors) {
                if ((constructor.getParameterCount() > 0) && ((Arrays.stream(constructor.getParameterTypes()).filter(aClass -> aClass.equals(intf)).count()>=1))
                        && ((Arrays.stream(constructor.getAnnotations()).count() == 0) ||
                        (Arrays.stream(constructor.getAnnotations()).noneMatch(annotation -> annotation.annotationType().getSimpleName().equals("Inject"))))) {
                    try {
                        throw new ConstructorNotFoundException();
                    } catch (ConstructorNotFoundException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                Annotation[] annotations = constructor.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().getSimpleName().equals("Inject")) {
                        countOfAnnotatedConstructors++;
                        if (countOfAnnotatedConstructors > 1) {
                            try {
                                throw new TooManyConstructorsException();
                            } catch (TooManyConstructorsException e) {
                               e.printStackTrace();
                                return;
                            }
                        }
                        if ((constructor.getParameterCount() == 1) && (Arrays.stream(constructor.getParameterTypes()).findFirst().get().equals(intf))) {
                           Object obj = constructor.newInstance(impl.newInstance());
                            InjectedInstances.addInstance(clazz, obj);
                        }
                    }
                }
            }
        }
    }
}
