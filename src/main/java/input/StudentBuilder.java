package input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentBuilder implements Loader<Student>{
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
            Student student=new Student.StudentBuilder().groupNumber(Integer.parseInt(elem[0])).averageScore(Double.parseDouble(elem[1])).recordBookNumber(elem[2]).build();
            if(validator.isValid(student)){
                students.add(student);
            }
        }
        return students;
    }
}
