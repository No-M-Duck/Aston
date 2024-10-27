package startegy;

import models.User;

import java.util.Comparator;

public class UserComparator {

    public static class SortName implements Comparator<User> {
        @Override
        public int compare(User u1, User u2){
            return u1.getName().compareToIgnoreCase(u2.getName());
        }
    }

    public static class SortPassword implements Comparator<User> {
        @Override
        public int compare(User u1, User u2){
            return u1.getPassword().compareToIgnoreCase(u2.getPassword());
        }
    }

    public static class SortEmail implements Comparator<User> {
        @Override
        public int compare(User u1, User u2){
            return u1.getEmail().compareToIgnoreCase(u2.getEmail());
        }
    }
}
