package ConsoleController;

import input.BusLoader;
import input.Loader;
import input.StudentLoader;
import input.UserLoader;
import models.Bus;
import models.Student;
import models.User;
import strategy.BusComparator;
import strategy.SelectionSortStrategy;
import strategy.SortStrategy;

import java.io.IOException;
import java.util.*;

public class ConsoleController {
    private Scanner scan = new Scanner(System.in);

    public void run() {

        while (true) {
            int answer = printMainMenu();
            switch (answer) {
                case 0:
                    return;
                case 1:
                    BusController busController = new BusController();
                    if(busController.menuBusCreate()!=0){
                        busController.menuBusSort();
                    }
                    break;
                case 2:
                    UserController userController = new UserController();
                    if(userController.menuUserCreate()!=0){
                        userController.menuUserSort();
                    }
                    break;
                case 3:
                    StudentController studentController = new StudentController();
                    if(studentController.menuStudentCreate()!=0){
                        studentController.menuStudentSort();
                    }
                    break;
                default:
                    System.out.println("Каким-то образом вы сломали предыдущее меню\nНо ничего, мы смогли вас поймать, продолжим работу");
            }
        }


    }

    private int printMainMenu() {
        System.out.println("* * * Главное меню * * *\nВыберите тип данных с которыми мы будем работать");
        System.out.println("0.Выход");
        System.out.println("1.Автобус(Bus)");
        System.out.println("2.Пользователь(User)");
        System.out.println("3.Студент(Student)");

        int answer = -1;

        while (true) {
            try {
                System.out.print("Введите номер: ");
                answer = scan.nextInt();
                if (answer >= 0 && answer <= 3) {
                    break;
                } else {
                    System.out.println("Ошибка: выберите значение от 0 до 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число.");
                scan.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Ошибка: данные для ввода отсутствуют.");
                break;
            }
        }

        return answer;
    }

}