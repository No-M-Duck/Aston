package ConsoleController;

import Validation.StudentValidator;
import input.StudentLoader;
import models.Student;
import output.StudentWriter;
import strategy.BinarySearcher;
import strategy.StudentComparator;
import strategy.SelectionSortStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

public class StudentController extends AbstService<Student>{


    private static List<Student> data = new ArrayList<>();


    public int menuStudentCreate() {
        int menuCount = super.menuCreate();
        int answer = super.getAnswer(menuCount);
        StudentLoader StudentLoader = new StudentLoader();
        switch (answer) {
            case 0:
                return 0;
            case 1:
                System.out.println("Введите количество студентов");
                int count = super.getAnswer(Integer.MAX_VALUE);
                data = StudentLoader.loadRnd(count);
                break;

            case 2:
                System.out.println("Введите полное имя файла(example.csv)");
                System.out.println("Доступные расширения файлов: .csv");
                System.out.println("Если ваш файл находится не в директории программы, введите полный путь к файлу");
                String path = "output\\" + scanner.next();
                try {
                    data = StudentLoader.loadFile(path);
                } catch (NumberFormatException |
                         ArrayIndexOutOfBoundsException|
                         IOException exception) {
                    System.out.println("Мы не смогли найти ваш файл или ваш файл содержит некорректные данные");
                    System.out.println("Попробуйте выполнить несколько предыдущих шагов снова");
                    return menuStudentCreate();
                }
                break;
            case 3:
                data = StudentLoader.loadConsole();
                if(data.isEmpty()) return 0;
                break;
            default:
                defChoice();

        }
        checkData(data);
        return 1;
    }



    public void menuStudentSort() {
        localExit = true;
        while (localExit) {
            int menucount = super.menuFunctions();
            int answer = super.getAnswer(menucount);
            switch (answer) {
                case 0:
                    return;
                case 1:
                    menuStudentSortField(data);
                    break;
                case 2:
                    menuStudentSearch();
                    break;
                case 3:
                    checkData(data);
                    continue;
                case 4: {
                    try {
                        StudentWriter writer = new StudentWriter();
                        writer.toFile(data);
                    } catch (IOException e) {
                        System.out.println("Ошибка записи файла");
                    }
                    break;
                }
                case 5: {
                    try {
                        StudentWriter writer = new StudentWriter();
                        writer.toFile(super.foundElement);
                        super.foundElement = null;
                    } catch (IOException e) {
                        System.out.println("Ошибка записи файла");
                    }
                    break;
                }
                default:
                    defChoice();
            }
        }
    }



    private void menuStudentSortField(List<Student> data) {
        int menucount = super.menuSortS();
        int answer = super.getAnswer(menucount);
        SelectionSortStrategy<Student> sortStrategy = new SelectionSortStrategy<>();
        switch (answer) {
            case 0:
                return;
            case 1:
                sortStrategy.sort(data, StudentComparator.groupNumber());
                return;
            case 2:
                sortStrategy.sort(data, StudentComparator.groupNumber(true));
                return;
            case 3:
                sortStrategy.sort(data, StudentComparator.averageScore());
                return;
            case 4:
                sortStrategy.sort(data, StudentComparator.averageScore(true));
                return;
            case 5:
                sortStrategy.sort(data, StudentComparator.recordBookNumber());
                return;
            case 6:
                sortStrategy.sort(data, StudentComparator.recordBookNumber(true));
                return;
            default:
                defChoice();
        }
    }
    private void menuStudentSearch(){
        int menuCount = super.menuSearchS();
        int answer = super.getAnswer(menuCount);
        int result = -1;
        Student search = createStudentForSearch(answer);
        Student resultStudent = null;
        switch (answer){
            case 0:
                return;
            case 1:
                result = BinarySearcher.findIndex(data,search,StudentComparator.groupNumber());
                break;
            case 2:
                result = BinarySearcher.findIndex(data,search,StudentComparator.averageScore());
                break;
            case 3:
                result = BinarySearcher.findIndex(data,search,StudentComparator.recordBookNumber());
                break;
            case 4:
                resultStudent = BinarySearcher.findElement(data,search,StudentComparator.groupNumber());
                break;
            case 5:
                resultStudent = BinarySearcher.findElement(data,search,StudentComparator.averageScore());
                break;
            case 6:
                resultStudent = BinarySearcher.findElement(data,search,StudentComparator.recordBookNumber());
                break;
            default:
                defChoice();
        }
        if(result>-1){
            System.out.println("Мы нашли его. Индекс вашего элемента: "+result+"; позиция в списке: "+(result+1));
            super.foundElement = data.get(result);
        }else if(resultStudent!=null){
            System.out.println("Мы нашли его. Ваш элемент: "+resultStudent);
            super.foundElement = resultStudent;
        }else {
            System.out.println("Ничего не обнаружено.Попробуем снова");
            menuStudentSearch();
        }
    }

    private Student createStudentForSearch(int choice){
        StudentValidator StudentValidator = new StudentValidator();
        Student Student = new Student.StudentBuilder().build();
        scanner.nextLine();
        if (choice==0){
            return Student;
        }
        if(choice%3==0){
            System.out.print("Введите номер зачетной книжки: ");
            try {
                int record = scanner.nextInt();
                Student = new Student.StudentBuilder().recordBookNumber(record).build();
            }catch (NoSuchElementException exception){
                System.out.println("Ошибка ввода, а значит...");
            }

            if(StudentValidator.isRecordBookNumberValid(Student)){
                return Student;
            }else {
                System.out.println("По вашим данным невозможно найти элемент");
                return Student;
            }
        }
        if(choice%3==2){
            System.out.print("Введите средний балл: ");
            try {
                scanner.useLocale(Locale.UK);
                double avg = scanner.nextDouble();
                Student = new Student.StudentBuilder().avgScore(avg).build();
            }catch (NoSuchElementException exception){
                System.out.println("Ошибка ввода, а значит...");
            }

            if(StudentValidator.isAvgScoreValid(Student)){
                return Student;
            }else {
                System.out.println("По вашим данным невозможно найти элемент");
                return Student;
            }
        }
        if (choice%3==1){
            System.out.print("Введите номер группы: ");
            try {
                int group = scanner.nextInt();
                Student = new Student.StudentBuilder().groupNumber(group).build();
            }catch (NoSuchElementException exception){
                System.out.println("Ошибка ввода, а значит...");
            }

            if(StudentValidator.isNumberGroupValid(Student)){
                return Student;
            }else {
                System.out.println("По вашим данным невозможно найти элемент");
                return Student;
            }
        }
        return Student;
    }

}
