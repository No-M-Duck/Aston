package input;

import Validation.StudentValidator;
import Validation.Validator;
import com.github.javafaker.Faker;
import models.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentLoader implements Loader<Student>{

    public List<Student> loadFile(String filePath) throws IllegalArgumentException, IOException {
        List<Student> students = new ArrayList<>();
        List<String> data = Files.readAllLines(Path.of(filePath));
        for (String line : data) {
            String[] parsedLine = line.split(",");

            int groupNumber = Integer.parseInt(parsedLine[0]);
            double averageScore = Double.parseDouble(parsedLine[1]);
            int recordBookNumber = Integer.parseInt(parsedLine[2]);

            students.add(new Student.StudentBuilder()
                    .groupNumber(groupNumber)
                    .avgScore(averageScore)
                    .recordBookNumber(recordBookNumber)
                    .build());
        }
        return students;
    }


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
