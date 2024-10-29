package Validation;

import models.User;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class UserValidator implements Validator<User> {

    private final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private final Pattern pattern = Pattern.compile(emailRegex);
    private static final Logger logger = Logger.getLogger(UserValidator.class.getName());

    @Override
    public boolean isValid(User user) {
        return isNameValid(user) && isPasswordValid(user) && isEmailValid(user);
    }



    public boolean isNameValid(User user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            logInvalidUser(user, Level.SEVERE, "User name is invalid or empty");
            return false;
        }
        return true;
    }

    public boolean isPasswordValid(User user) {
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            logInvalidUser(user, Level.SEVERE, "Password is invalid or empty");
            return false;
        }
        return true;
    }

    public boolean isEmailValid(User user) {
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            logInvalidUser(user, Level.SEVERE, "Email is invalid or empty");
            return false;
        }
        if (!pattern.matcher(user.getEmail()).matches()) {
            logInvalidUser(user, Level.SEVERE, "Email incorrect format: " + user.getEmail());
            return false;
        }
        return true;
    }

    private void logInvalidUser(User user, Level level, String message) {
        String logMessage = String.format("User validation failed: %s. User details: %s", message, user.toString());
        logger.log(level, logMessage);
    }
}
