package sbertech.hw1.shapes;

import java.security.InvalidParameterException;

public class Rectangle extends Shape {
    private double sideA;
    private double sideB;

    public Rectangle(double a, double b) {
        if (a <= 0) throw new InvalidParameterException("'A' must be a positive number");
        if (b <= 0) throw new InvalidParameterException("'B' must be a positive number");
        sideA = a;
        sideB = b;
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    @Override
    public double perimeter() {
        return (getSideA() + getSideB()) * 2;
    }

    @Override
    public double area() {
        return getSideA() * getSideB();
    }
}
