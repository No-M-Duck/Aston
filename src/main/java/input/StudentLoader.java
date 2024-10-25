package input;

import Validation.StudentValidator;
import Validation.Validator;
import com.github.javafaker.Faker;
import models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentLoader implements Loader<Student>{


    @Override
    public List<Student> loadConsole() {
        return null;
    }

    @Override
    public List<Student> loadRnd(int count) {
        Faker faker = Loader.getFaker();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            students.add(
                    new Student.StudentBuilder()
                            .avgScore(faker.number().randomDouble(2,0,10))
                            .groupNumber(faker.number().randomDigitNotZero())
                            .recordBookNumber(faker.number().numberBetween(10000000,99999999))
                            .build()
            );
        }
        return students;
    }
}
