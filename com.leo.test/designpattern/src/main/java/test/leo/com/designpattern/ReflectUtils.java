package test.leo.com.designpattern;

import java.lang.reflect.Field;

/**
 * Created by leo on 16-2-2.
 */
public class ReflectUtils {
  public static Field findField(Object instance, String name)
      throws NoSuchFieldException {
    for (Class clazz = instance.getClass(); clazz != null;) {
      try {
        Field field = clazz.getDeclaredField(name);
        if (!field.isAccessible()) {
          field.setAccessible(true);
        }
        return field;
      } catch (NoSuchFieldException e) {
        clazz = clazz.getSuperclass();
      }
    }
    throw new NoSuchFieldException("Field" + name + "not found in"
        + instance.getClass());
  }
}
