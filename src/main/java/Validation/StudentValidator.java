package Validation;

import models.Student;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentValidator implements Validator<Student> {

    private static final Logger logger = Logger.getLogger(StudentValidator.class.getName());

    @Override
    public boolean isValid(Student student) {
        return isAvgScoreValid(student) && isNumberGroupValid(student) && isRecordBookNumberValid(student);
    }

    private boolean isAvgScoreValid(Student student) {
        if (student.getAvgScore() <= 0 || student.getAvgScore() > 10) {
            logInvalidStudent(student, Level.SEVERE, "Average value of the student's grade is out of valid range (0-10)");
            return false;
        }
        return true;
    }

    private boolean isNumberGroupValid(Student student) {
        if (student.getGroupNumber() <= 0) {
            logInvalidStudent(student, Level.SEVERE, "Number group is negative or zero");
            return false;
        }
        return true;
    }

    private boolean isRecordBookNumberValid(Student student) {
        if (student.getRecordBookNumber() <= 0) {
            logInvalidStudent(student, Level.SEVERE, "Record book number is negative or zero");
            return false;
        }
        return true;
    }

    private void logInvalidStudent(Student student, Level level, String message) {
        String logMessage = String.format("Student validation failed: %s. Student details: %s", message, student.toString());
        logger.log(level, logMessage);
    }
}
