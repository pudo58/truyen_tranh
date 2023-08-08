package vn.tholv.web.core.override.util;

import java.lang.reflect.Field;

@SuppressWarnings("all")
public class CloneObject<T> {
    public T cloneObject() {
        try {
            Class<?> clazz = (Class<?>) this.getClass();
            Field[] fields = clazz.getDeclaredFields();
            T object = (T) clazz.newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(object, field.get(this));
            }
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void copyData(T object) {
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(this, field.get(object));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
