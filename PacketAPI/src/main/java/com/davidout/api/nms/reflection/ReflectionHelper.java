package com.davidout.api.nms.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionHelper {

    public static Field getField(Class<?> classToGetFieldFrom, String field)  {
        try {
            Field f = classToGetFieldFrom.getDeclaredField(field);
            f.setAccessible(true);
            return f;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static Method getMethod(Class<?> classToGetMethodFrom, String method, Class<?>... parameterClasses) {
        try {
            return classToGetMethodFrom.getMethod(method, parameterClasses);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static Constructor<?> getConstructor(Class<?> classToGetConstructorFrom, Class<?>... parameterClasses) {
        try {
            return classToGetConstructorFrom.getConstructor(parameterClasses);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }



}
