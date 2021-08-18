package sbertech.hw1.temperature;

public class Temperature {
    private double value;
    private TemperatureScale scale;

    public Temperature(double v, TemperatureScale t) {
        value = v;
        scale = t;
    }

    public double getTemperatureValue() {
        return value;
    }

    public TemperatureScale getTemperatureScale() {
        return scale;
    }
}
