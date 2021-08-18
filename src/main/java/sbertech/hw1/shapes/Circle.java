package sbertech.hw1.shapes;

import java.security.InvalidParameterException;

public class Circle extends Shape{
    private double radius;

    public Circle(double r) {
        if (r <= 0) throw new InvalidParameterException("Radius must be a positive number");
        radius = r;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double perimeter() {
        return Math.PI * getRadius() * 2;
    }

    @Override
    public double area() {
        return Math.PI * getRadius() * getRadius();
    }
}
