package sbertech.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sbertech.hw5.bean_utils.BeanUtils;
import sbertech.hw5.bean_utils.ClassA;
import sbertech.hw5.bean_utils.ClassB;

import java.lang.reflect.InvocationTargetException;

public class BeanUtilsTest {
    @Test
    public void getterSetterTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ClassA a = new ClassA(-10, 10, "ten");
        ClassB b = new ClassB();

        Assertions.assertNotEquals(b.fields().get(0), a.fields().get(0));
        Assertions.assertNotEquals(b.fields().get(1), a.fields().get(1));
        Assertions.assertNotEquals(b.fields().get(2), a.fields().get(2));

        BeanUtils.assign(b, a);

        Assertions.assertNotEquals(b.fields().get(0), a.fields().get(0));
        Assertions.assertNotEquals(b.fields().get(1), a.fields().get(1));
        Assertions.assertEquals(b.fields().get(2), a.fields().get(2));

        b.setValue(-20);
        b.setObject("twenty");

        BeanUtils.assign(a, b);

        Assertions.assertNotEquals(b.fields().get(0), a.fields().get(0));
        Assertions.assertNotEquals(b.fields().get(1), a.fields().get(1));
        Assertions.assertNotEquals(b.fields().get(2), a.fields().get(2));
    }
}
