package sbertech.hw5.bean_utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) throws InvocationTargetException, IllegalAccessException {
        List<List<Method>> getterSetterPair = new ArrayList<>();
        for (int i = 0; i < from.getClass().getMethods().length; i++) {
            for (int j = 0; j < to.getClass().getMethods().length; j++) {
                Method methodFrom = from.getClass().getMethods()[i];
                Method methodTo = to.getClass().getMethods()[j];
                if (methodFrom.getName().substring(1).equals(methodTo.getName().substring(1))
                        && methodFrom.getName().charAt(0) == 'g'
                        && methodTo.getName().charAt(0) == 's'
                        && methodTo.getReturnType().getName().equals("void")
                        && methodTo.getParameterCount() == 1
                        && methodFrom.getParameterCount() == 0
                        && methodTo.getParameters()[0].getType().isAssignableFrom(methodFrom.getReturnType())
                ) {
                    getterSetterPair.add(List.of(methodFrom, methodTo));
                }
            }
        }
        for (List<Method> methods : getterSetterPair) {
            System.out.printf("Found getter-setter pair: %s and %s\n",
                    methods.get(0).getName(),
                    methods.get(1).getName());
            methods.get(1).invoke(to, methods.get(0).invoke(from));
        }
    }
}
