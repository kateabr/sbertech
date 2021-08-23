package sbertech.hw2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Car {
    private String model;
    private String type;

    public Car(String m, String t) {
        model = m;
        type = t;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(model, car.model) && Objects.equals(type, car.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, type);
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList<>(List.of(
                new Car("Лада", "седан"),
                new Car("Лада", "хэтчбек"),
                new Car("Мерседес", "седан"),
                new Car("Бмв", "кроссовер"),
                new Car("Форд", "хэтчбек"),
                new Car("Пежо", "кроссовер"),
                new Car("Тойота", "седан")));
        Collections.shuffle(cars);

        System.out.println(cars.stream().collect(Collectors.groupingBy(Car::getType)));
    }
}
