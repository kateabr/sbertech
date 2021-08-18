package sbertech.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sbertech.hw1.shapes.*;

import java.util.List;
import java.util.stream.Collectors;

public class ShapesTest {
    @Test
    public void testShapesPerimeter() {
        Assertions.assertEquals(List.of(Math.PI * 20.0, 40.0, 30.0, 9.0),
                List.of(new Circle(10), new Square(10), new Rectangle(10, 5), new Triangle(2,3,4))
                        .stream().map(Shape::perimeter).collect(Collectors.toList()));
    }

    @Test
    public void testShapesArea() {
        Assertions.assertEquals(List.of(Math.PI * 100.0, 100.0, 50.0, Math.sqrt(4.5 * 2.5 * 1.5 * 0.5)),
                List.of(new Circle(10), new Square(10), new Rectangle(10, 5), new Triangle(2,3,4))
                        .stream().map(Shape::area).collect(Collectors.toList()));
    }
}
