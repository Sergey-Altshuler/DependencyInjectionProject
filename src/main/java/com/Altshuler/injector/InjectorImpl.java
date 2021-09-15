package com.Altshuler.injector;

import com.Altshuler.customException.BindingNotFoundException;
import com.Altshuler.customException.ConstructorNotFoundException;
import com.Altshuler.customException.TooManyConstructorsException;
import com.Altshuler.info.ProjectInfo;
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
        if (!ProjectInfo.bindingMap.containsKey(type))
            return null;
        else {
            Class clazz = ProjectInfo.bindingMap.get(type);
            return new ProviderImpl<>(clazz);
        }
    }

    @Override
    public <T> void bind(Class<T> intf, Class<? extends T> impl) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        List<Class> classList = ProjectInfo.scannedList;
        for (Class clazz : classList) {
            Constructor[] constructors = clazz.getConstructors();
            int countOfAnnotatedConstructors = 0;

            for (Constructor constructor : constructors) {
                Annotation[] annotations = constructor.getDeclaredAnnotations();
                if ((constructor.getParameterCount()>0)&&((Arrays.stream(constructor.getParameterTypes()).findFirst().get().equals(intf))
                &&(Arrays.stream(constructor.getAnnotations()).count()==0 ||
                !Arrays.stream(constructor.getAnnotations()).findAny().get().annotationType().getSimpleName().equals("Inject"))))
                {
                    try {
                        throw new ConstructorNotFoundException();
                    }
                    catch (ConstructorNotFoundException e){
                        System.out.println(e.getMessage());
                        return;
                    }
                }
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().getSimpleName().equals("Inject")) {
                        countOfAnnotatedConstructors++;
                        if (constructor.getParameterCount() > 1) {
                            try {
                                throw new BindingNotFoundException();
                            } catch (BindingNotFoundException e) {
                                System.out.println(e.getMessage());
                                return;
                            }
                        }

                        if ((constructor.getParameterCount()>0)&&(Arrays.stream(constructor.getParameterTypes()).findFirst().get().equals(intf))) {
                            constructor.newInstance(impl.newInstance());
                            ProjectInfo.bindingMap.put(intf, impl);
                        }
                    }
                }
                if (countOfAnnotatedConstructors > 1) {
                    try {
                        throw new TooManyConstructorsException();
                    } catch (TooManyConstructorsException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                }
            }
        }
    }
}
