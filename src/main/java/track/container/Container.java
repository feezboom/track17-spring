package track.container;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.ws.util.StringUtils;
import sun.misc.FloatingDecimal;
import track.container.config.Bean;
import track.container.config.Property;
import track.container.config.ValueType;

/**
 * Основной класс контейнера
 * У него определено 2 публичных метода, можете дописывать свои методы и конструкторы
 */
public class Container {

    private List<Bean> beans;
    private Map<String, Object> objById;
    private Map<String, Object> objByClassName;

    // Реализуйте этот конструктор, используется в тестах!
    public Container(List<Bean> beans) throws ClassNotFoundException, NoSuchMethodException {
        this.beans = beans;
        objByClassName = new HashMap<String, Object>();
        objById = new HashMap<String, Object>();
    }

    /**
     *  Вернуть объект по имени бина из конфига
     *  Например, Car car = (Car) container.getById("carBean")
     */
    public Object getById(String id) throws Exception {
        for (Bean bean : beans) {
            String currentId = bean.getId();

            if (currentId.equals(id)) {

                Object neededObject = objById.get(currentId);

                if (neededObject == null) {
                    String className = bean.getClassName();
                    Class<?> clazz = Class.forName(className);

                    Map<String, Property> propertiesMap =  bean.getProperties();

                    neededObject = initializeInstance(clazz, propertiesMap);
                    objById.put(currentId, neededObject);
                }

                return neededObject;
            }
        }
        return null;
    }

    /**
     * Вернуть объект по имени класса
     * Например, Car car = (Car) container.getByClass("track.container.beans.Car")
     */
    public Object getByClass(String className) throws Exception {
        for (Bean bean : beans) {
            String neededClassName = bean.getClassName();

            if (neededClassName.equals(className)) {
                Object neededObject = objByClassName.get(neededClassName);

                if (neededObject == null) {
                    Class<?> clazz = Class.forName(className);
                    Map<String, Property> propertiesMap =  bean.getProperties();
                    neededObject = initializeInstance(clazz, propertiesMap);
                    objByClassName.put(neededClassName, neededObject);
                }

                return neededObject;
            }
        }
        return null;
    }

    private Object initializeInstance(Class<?> clazz, Map<String, Property> propertiesMap) throws Exception {
        Object instance = clazz.newInstance();

        for (Map.Entry<String, Property> entry : propertiesMap.entrySet()) {
            String propertyName = entry.getKey();

            String propertyFieldName = entry.getValue().getName();
            String propertyFieldValue = entry.getValue().getValue();
            ValueType propertyFieldType = entry.getValue().getType();

            String capitalizedFieldName = StringUtils.capitalize(propertyFieldName);

            instance = setNeededValue(clazz, instance, capitalizedFieldName, propertyFieldValue, propertyFieldType);
        }

        return instance;
    }

    private Object setNeededValue(Class<?> clazz, Object instance, String classFieldName,
                                  String value, ValueType valueType) throws Exception {
        Method setter;

        if (valueType == ValueType.REF) {

            Object realFieldValue = getById(value);
            setter = clazz.getMethod("set" + classFieldName, realFieldValue.getClass());
            instance = setter.invoke(instance, realFieldValue);

        } else { // if (propertyFieldType == ValueType.VAL) {

            if (getTypeOf(value).equals("int")) {

                setter = clazz.getMethod("set" + classFieldName, int.class);
                instance = setter.invoke(instance, Integer.parseInt(value));

            } else { // if (getTypeOf(classFieldName).equals("float")) {

                setter = clazz.getMethod("set" + classFieldName, float.class);
                instance = setter.invoke(instance, Float.parseFloat(value));

            }
        }

        return instance;
    }

    private String getTypeOf(String value) throws Exception {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException ex0) {
            try {
                Float.parseFloat(value);
            } catch (NumberFormatException ex1) {
                throw new Exception("Given value is not primitive type.");
            }
            return "float";
        }
        return "int";
    }

}
