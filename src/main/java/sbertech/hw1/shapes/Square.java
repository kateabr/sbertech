package sbertech.hw1.shapes;

import java.security.InvalidParameterException;

public class Square extends Shape{
    private double side;

    public Square(double s) {
        if (s <= 0) throw new InvalidParameterException("Square side must be a positive number");
        side = s;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double perimeter() {
        return getSide() * 4;
    }

    @Override
    public double area() {
        return getSide() * getSide();
    }
}
