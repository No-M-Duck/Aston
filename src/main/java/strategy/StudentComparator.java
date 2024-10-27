package strategy;

import models.Student;

import java.util.Comparator;

public class StudentComparator {

    public static Comparator<Student> groupNumber() {
        return (s1, s2) -> Integer.compare(s1.getGroupNumber(), s2.getGroupNumber());
    }

    public static Comparator<Student> groupNumber (boolean ignoreOdd) {
        return (s1, s2) -> {
            if (ignoreOdd) {
                if (s1.getGroupNumber() % 2 != 0 || s2.getGroupNumber() % 2 != 0) {
                    return 0;
                }
            }
            return Integer.compare(s1.getGroupNumber(), s2.getGroupNumber());
        };
    }

    public static Comparator<Student> averageScore() {
        return (s1, s2) -> Double.compare(s1.getAvgScore(), s2.getAvgScore());
    }

    public static Comparator<Student> recordBookNumber() {
        return (s1, s2) -> Integer.compare(s1.getRecordBookNumber(), s2.getRecordBookNumber());
    }

    public static Comparator<Student> recordBookNumber (boolean ignoreOdd) {
        return (s1, s2) -> {
            if (ignoreOdd) {
                if (s1.getRecordBookNumber() % 2 != 0 || s2.getRecordBookNumber() % 2 != 0) {
                    return 0;
                }
            }
            return Integer.compare(s1.getRecordBookNumber(), s2.getRecordBookNumber());
        };
    }
}