package mcapi.davidout.utillity.packet.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionHelper {

    public static Field getField(Object classToGetFieldFrom, String field) throws NoSuchFieldException, SecurityException {
        Field f;
        f = classToGetFieldFrom.getClass().getDeclaredField(field);
        f.setAccessible(true);
        return f;
    }

    public static Method getMethod(Class<?> classToGetMethodFrom, String method, Class<?>... parameterTypes) {
        try {
            return classToGetMethodFrom.getMethod(method, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }



}
