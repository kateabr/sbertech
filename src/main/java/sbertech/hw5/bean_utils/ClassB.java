package sbertech.hw5.bean_utils;

import java.util.ArrayList;
import java.util.List;

public class ClassB {
    private Number value;
    private Double value1;
    private Object object;

    public ClassB() { }

    public void setValue(Number v) {
        value = v;
    }

    public void setValue1(Double value1) {
        this.value1 = value1;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Number getValue() {
        return value;
    }

    public Double getValue1() {
        return value1;
    }

    public Object getObject() {
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
        return "ClassB{" +
                "value=" + value +
                ", value1=" + value1 +
                ", object=" + object +
                '}';
    }
}
