package sbertech.hw5.bean_utils;

import java.util.ArrayList;
import java.util.List;

public class ClassA {
    private Integer value;
    private Integer value1;
    private String object;

    public ClassA(Integer v, Integer v1, String o) {
        value = v;
        value1 = v1;
        object = o;
    }

    private Integer getValue() {
        return value;
    }

    public Integer getValue1() {
        return value1;
    }

    public String getObject() {
        return object;
    }

    public List<Object> fields() {
        ArrayList<Object> res = new ArrayList<>();
        res.add(value);
        res.add(value1);
        res.add(object);
        return res;
    }

    @Override
    public String toString() {
        return "ClassA{" +
                "value=" + value +
                ", value1=" + value1 +
                ", object='" + object + '\'' +
                '}';
    }
}
