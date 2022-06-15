package loader.access;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class RSClassLoader extends URLClassLoader
{
    public RSClassLoader(File file) throws MalformedURLException
    {
        super(new URL[]{file.toURI().toURL()});
    }

    @Override
    public Class<?> loadClass(String name)
    {
        try
        {
            return super.loadClass(name, false);
        } catch (ClassNotFoundException e)
        {
            System.out.println("Class not found " + name);
        }
        return null;
    }

    /**
     * Gets a field value from this RSClassLoader
     *
     * @param className - the class name
     * @param fieldName - the field name
     * @param instance  - an instance of the class (if static field, send null)
     */
    public Object getFieldValue(String className, String fieldName, Object instance)
    {
        try
        {
            Field field = loadClass(className).getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(instance);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sets a field value from this RSClassLoader
     *
     * @param className - the class name
     * @param fieldName - the field name
     * @param value     - the value to set it
     * @param instance  - an instance of the class (if static field, send null)
     */
    public void setFieldValue(String className, String fieldName, Object instance, Object value)
    {
        try
        {
            Field field = loadClass(className).getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setArrayValue(String className, String fieldName, Object instance, Object value, int index)
    {
        try
        {
            Field field = loadClass(className).getDeclaredField(fieldName);
            field.setAccessible(true);
            Object array = field.get(instance);
            Array.set(array, index, value);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Object invokeMethod(String className, String methodName, Class<?>[] params, Object instance, Object... args)
    {
        try
        {
            Method method = loadClass(className).getDeclaredMethod(methodName, params);
            if (method != null)
            {
                method.setAccessible(true);
                return method.invoke(instance, args);
            }
            else
                System.out.println("NULL METHOD");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
