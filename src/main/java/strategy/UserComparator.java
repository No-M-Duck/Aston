package strategy;

import models.User;

import java.util.Comparator;

public class UserComparator {

    public static Comparator<User> name() {
        return (u1, u2) -> u1.getName().compareToIgnoreCase(u2.getName());
    }

    public static Comparator<User> password() {
        return (u1, u2) -> u1.getPassword().compareToIgnoreCase(u2.getPassword());
    }

    public static Comparator<User> email() {
        return (u1, u2) -> u1.getEmail().compareToIgnoreCase(u2.getEmail());
    }

}