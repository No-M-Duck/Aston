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

            Student student = new Student.StudentBuilder()
                    .groupNumber(groupNumber)
                    .avgScore(averageScore)
                    .recordBookNumber(recordBookNumber)
                    .build();

            Validator<Student> validator = new StudentValidator();
            if (validator.isValid(student)) {
                students.add(student);
            }
        }
        return students;
    }

    @Override
    public List<Student> loadConsole() {
        List<Student> students=new ArrayList<>();
        List<String[]> inputArray=new ArrayList<>();
        List<String> errorInput=new ArrayList<>();

        Scanner scan=new Scanner(System.in);

        System.out.println("Для создания User введите данные следующим образом:");
        System.out.println("Номер группы, Средний балл, Номер зачетной книжки");
        System.out.println("ВНИМАНИЕ! Ввод происходит до введения пустой строки!");

        while(true){
            String input=scan.nextLine();
            if (input.equals("")){
                break;
            }
            String[] studentString=input.split(", ");
            if(studentString.length==3){
                inputArray.add(studentString);
            }
            else {
                errorInput.add(input);
            }
        }

        if (!errorInput.isEmpty()){
            System.out.println("Необработанные строки:");
            for (String err:errorInput){
                System.out.println(err);
            }
        }

        Validator<Student> validator=new StudentValidator();

        for (String[] elem:inputArray){
            Student student=new Student.StudentBuilder().groupNumber(Integer.parseInt(elem[0])).avgScore(Double.parseDouble(elem[1])).recordBookNumber(Integer.parseInt(elem[2])).build();
            if(validator.isValid(student)){
                students.add(student);
            }
        }
        return students;
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
