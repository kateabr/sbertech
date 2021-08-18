package sbertech.hw1;

import org.decimal4j.util.DoubleRounder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sbertech.hw1.temperature.Temperature;
import sbertech.hw1.temperature.TemperatureConverter;
import sbertech.hw1.temperature.TemperatureScale;

import java.util.List;
import java.util.stream.Collectors;

public class TemperatureTest {
    @Test
    public void testToFahrenheit() {
        Assertions.assertEquals(List.of(12.0, 113.0, 32.0),
                List.of(new Temperature(12.0, TemperatureScale.Fahrenheit),
                        new Temperature(45.0, TemperatureScale.Celsius),
                        new Temperature(273.15, TemperatureScale.Kelvin))
                        .stream()
                        .map(t -> DoubleRounder.round(TemperatureConverter.toFahrenheit(t), 3))
                        .collect(Collectors.toList()));
    }

    @Test
    public void testToCelsius() {
        Assertions.assertEquals(List.of(20.0, 45.0, -73.0),
                List.of(new Temperature(68.0, TemperatureScale.Fahrenheit),
                        new Temperature(45.0, TemperatureScale.Celsius),
                        new Temperature(200.15, TemperatureScale.Kelvin))
                        .stream()
                        .map(t -> DoubleRounder.round(TemperatureConverter.toCelsius(t), 3))
                        .collect(Collectors.toList()));
    }

    @Test
    public void testToKelvin() {
        Assertions.assertEquals(List.of(294.261, 421.15, 200.15),
                List.of(new Temperature(70.0, TemperatureScale.Fahrenheit),
                        new Temperature(148.0, TemperatureScale.Celsius),
                        new Temperature(200.15, TemperatureScale.Kelvin))
                        .stream()
                        .map(t -> DoubleRounder.round(TemperatureConverter.toKelvin(t), 3))
                        .collect(Collectors.toList()));
    }
}
