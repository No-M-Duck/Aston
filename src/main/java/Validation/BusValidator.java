package Validation;

import models.Bus;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class BusValidator implements Validator<Bus> {
    private static final String BUS_NUMBER_REGEX = "^[АВЕКМНОРСТУХ]{1}\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$";
    private static final Pattern pattern = Pattern.compile(BUS_NUMBER_REGEX);
    private static final Logger logger = Logger.getLogger(BusValidator.class.getName());

    @Override
    public boolean isValid(Bus bus) {
        return isModelValid(bus) && isNumberValid(bus) && isMileageValid(bus);
    }

    public boolean isModelValid(Bus bus) {
        if (bus.getModel() == null || bus.getModel().trim().isEmpty()) {
            logInvalidBus(bus, Level.SEVERE, "Bus model is invalid or empty");
            return false;
        }
        return true;
    }

    public boolean isNumberValid(Bus bus) {
        if (bus.getNumber() == null || bus.getNumber().trim().isEmpty()) {
            logInvalidBus(bus, Level.WARNING, "Invalid bus number: " + bus.getNumber());
            return false;
        }
        if (!pattern.matcher(bus.getNumber()).matches()) {
            logInvalidBus(bus, Level.WARNING, "Bus number incorrect format: " + bus.getNumber());
            return false;
        }
        return true;
    }

    public boolean isMileageValid(Bus bus) {
        if (bus.getMileage() < 0) {
            logInvalidBus(bus, Level.WARNING, "Bus mileage is negative: " + bus.getMileage());
            return false;
        }
        return true;
    }

    private void logInvalidBus(Bus bus, Level level, String message) {
        String logMessage = String.format("Bus validation failed: %s. Bus details: %s", message, bus.toString());
        logger.log(level, logMessage);
    }
}
