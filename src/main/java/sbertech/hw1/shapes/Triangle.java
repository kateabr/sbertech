package sbertech.hw1.shapes;

import java.security.InvalidParameterException;

public class Triangle extends Shape {
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double a, double b, double c) {
        if (a <= 0) throw new InvalidParameterException("'A' must be a positive number");
        if (b <= 0) throw new InvalidParameterException("'B' must be a positive number");
        if (c <= 0) throw new InvalidParameterException("'C' must be a positive number");
        sideA = a;
        sideB = b;
        sideC = c;
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getSideC() {
        return sideC;
    }

    @Override
    public double perimeter() {
        return getSideA() + getSideB() + getSideC();
    }

    @Override
    public double area() {
        double semiP = perimeter() / 2;
        return Math.sqrt(semiP * (semiP - getSideA()) * (semiP - getSideB()) * (semiP - getSideC()));
    }
}
