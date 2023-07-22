package snapje.packetapi.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {
	
	private NetMinecraftServer nms = new NetMinecraftServer();
		
	public Field getField(Object classToGetFieldFrom, String field) throws NoSuchFieldException, SecurityException {
		Field f;
		
		f = classToGetFieldFrom.getClass().getDeclaredField(field);
		f.setAccessible(true);
		
		return f;
	}
	public Class<?>[] getDeclaredClasses(Class<?> className) {
		return className.getDeclaredClasses();
	}
	public Method getMethod(Class<?> classToGetMethodFrom, String method) throws NoSuchMethodException, SecurityException {
		Method meth = null;
		
		for (Method me : classToGetMethodFrom.getMethods()) {
			if(me.toString().equalsIgnoreCase(method)) {
				meth = me;
				break;
			}
		}
		
		return meth;
	}
	public Constructor<?> getConstructor(String reflectionClass, Class<?> ... parameters) throws NoSuchMethodException, SecurityException {
		 Constructor<?> packetConstructor = nms.getNMSClass(reflectionClass).getConstructor(parameters);
		return packetConstructor;
	}
	
	public void setField(Object classToGetFieldFrom, String field, Object input) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f;
	
			f = classToGetFieldFrom.getClass().getDeclaredField(field);
			f.setAccessible(true);
			f.set(classToGetFieldFrom, input);
		
	
	}
	
	public Object createObjectWithConstructor(Constructor<?> construction, Object ... arguments) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object packet = construction.newInstance(arguments);
		return packet;
	}
	public Object useMethod(Object objectToGetClassFrom, String methodName, Object ... arguments) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Object returnedObject = null;
		returnedObject = objectToGetClassFrom.getClass().getMethod(methodName, arguments.getClass()).invoke(objectToGetClassFrom, arguments);
		
		return returnedObject;
	}
	
	
	
	

}
