package strategy;

import models.Bus;

import java.util.Comparator;

public class BusComparator {

    public static Comparator<Bus> number() {
        return (b1, b2) -> b1.getNumber().compareToIgnoreCase(b2.getNumber());
    }

    public static Comparator<Bus> model() {
        return (b1, b2) -> b1.getModel().compareToIgnoreCase(b2.getModel());
    }

    public static Comparator<Bus> mileage() {
        return (b1, b2) -> Integer.compare(b1.getMileage(), b2.getMileage());
    }

    public static Comparator<Bus> mileage(boolean ignoreOdd) {
        return (b1, b2) -> {
            if (ignoreOdd) {
                if (b1.getMileage() % 2 != 0 || b2.getMileage() % 2 != 0) {
                    return 0;
                }
            }
            return Integer.compare(b1.getMileage(), b2.getMileage());
        };
    }
}
