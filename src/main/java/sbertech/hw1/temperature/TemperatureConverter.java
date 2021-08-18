package sbertech.hw1.temperature;

public class TemperatureConverter {
    private TemperatureConverter() {}

    public static double toFahrenheit(Temperature temperature) {
        return switch (temperature.getTemperatureScale()) {
            case Fahrenheit -> temperature.getTemperatureValue();
            case Celsius -> (temperature.getTemperatureValue() * 9/5) + 32;
            case Kelvin -> (temperature.getTemperatureValue() - 273.15) * 9/5 + 32;
        };
    }

    public static double toCelsius(Temperature temperature) {
        return switch (temperature.getTemperatureScale()) {
            case Fahrenheit -> (temperature.getTemperatureValue() - 32) * 5/9;
            case Celsius -> temperature.getTemperatureValue();
            case Kelvin -> temperature.getTemperatureValue() - 273.15;
        };
    }

    public static double toKelvin(Temperature temperature) {
        return switch (temperature.getTemperatureScale()) {
            case Fahrenheit -> (temperature.getTemperatureValue() - 32) * 5/9 + 273.15;
            case Celsius -> temperature.getTemperatureValue() + 273.15;
            case Kelvin -> temperature.getTemperatureValue();
        };
    }
}
