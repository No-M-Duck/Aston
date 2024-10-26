package strategy;

import models.Student;

import java.util.Comparator;

public class StudentComparator {

    public static class SortGroupNumber implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            return Integer.compare(s1.getGroupNumber(), s2.getGroupNumber());
        }
    }

    public static class SortAverageScore implements Comparator<Student> {
        public int compare(Student s1, Student s2) {
            return Double.compare(s1.getAvgScore(), s2.getAvgScore());
        }
    }

    public static class SortRecordBookNumber implements Comparator<Student> {
        public int compare(Student s1, Student s2) {
            return Integer.compare(s1.getRecordBookNumber(), s2.getRecordBookNumber());
        }
    }

    public static class SortGroupNumberIgnoreOdd implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            if (s1.getGroupNumber() % 2 != 0 || s2.getGroupNumber() % 2 != 0) {
                return 0;
            }
            return Integer.compare(s1.getGroupNumber(), s2.getGroupNumber());
        }
    }
    public static class SortAverageScoreIgnoreOdd implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            if (s1.getAvgScore() % 2 != 0 || s2.getAvgScore() % 2 != 0) {
                return 0;
            }
            return Double.compare(s1.getAvgScore(), s2.getAvgScore());
        }
    }
    public static class SortRecordBookNumberIgnoreOdd implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            if (s1.getRecordBookNumber() % 2 != 0 || s2.getRecordBookNumber() % 2 != 0) {
                return 0;
            }
            return Integer.compare(s1.getRecordBookNumber(), s2.getRecordBookNumber());
        }
    }
}
