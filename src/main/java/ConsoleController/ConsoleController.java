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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleController {
    private Scanner scan = new Scanner(System.in);
    private List dataList;
    Class type;

    public void start(){
        while(true){
            printMainMenu();
            int choice=getChoice();
            boolean isEmpty=checkData();
            switch (choice){
                case 1:
                    setDataMenu();
                    break;
                case 2:
                    if (!isEmpty) {
                        sortMenu();
                        break;
                    }
                    System.out.println("Для начала необходиммо задать список!");
                    break;
                case 3:
                    if (!isEmpty) {
//                        findElement();
                        break;
                    }
                    System.out.println("Для начала необходиммо задать список!");
                    break;
                case 10:
                    dataList.stream().forEach(System.out::println);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Некорректный ввод.");
                    if (isEmpty){
                        System.out.println("Для начала необходиммо задать список!");
                    }
            }
        }
    }

    private boolean checkData(){
        return (dataList==null) ? true:false;
    }
    private int getChoice(){
        return scan.nextInt();
    }
    private void printMainMenu(){
        System.out.println("----Выберите действие----");
        System.out.println("1.Задать данные");
        System.out.println("2.Выбрать метод сортировки");
        System.out.println("3.Найти элемент(бинарный поиск)");
        System.out.println("10. Показать List(сервис)");
        System.out.println("4.Выход");
    }

    private void setDataMenu(){
        setType();
        if (type!=null){
            fillDataList(type);
        }
    }

    private void setType(){
        while (true) {
            printTypeMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    dataList = new ArrayList<User>();
                    type=User.class;
                    return;
                case 2:
                    dataList = new ArrayList<Student>();
                    type= Student.class;
                    return;
                case 3:
                    dataList = new ArrayList<Bus>();
                    type=Bus.class;
                    return;
                case 4:
//                    start();
                    return;
                default:
                    System.out.println("Некорректный ввод");
            }
        }
    }

    private void fillDataList(Class type) {
        Loader loader=null;
        if (type == Bus.class){
            loader=new BusLoader();
        }
        if(type == Student.class){
            loader=new StudentLoader();
        }
        if(type == User.class){
            loader=new UserLoader();
        }
        System.out.println(type);

        if (loader == null) {
            System.out.println("Ошибка: подходящий Loader не найден для типа " + type.getSimpleName());
            return;
        }

        while (true){
            printSetMenu();
            int choice=getChoice();
            switch (choice) {
                case 1:
                    System.out.println("Введите количество:");
                    int count=scan.nextInt();
                    dataList=loader.loadRnd(count);
                    return;
                case 2:
                    dataList=loader.loadConsole();
                    return;
                case 3:
                    System.out.println("Введите адрес файла:");
                    String filePath=scan.nextLine();
                    try {
                        dataList=loader.loadFile(filePath);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return;
                case 4:
                    return;
                default:
                    System.out.println("Некорректный ввод");
            }
        }
    }



    private void printTypeMenu(){
        System.out.println("----Выберите тип задаваемых данных----");
        System.out.println("1.Пользователь");
        System.out.println("2.Студент");
        System.out.println("3.Автобус");
        System.out.println("4.Назад");
    }

    private void printSetMenu(){
        System.out.println("----Выберите способо задания листа----");
        System.out.println("1.Рандом");
        System.out.println("2.Консольный ввод");
        System.out.println("3.Чтения из файла");
        System.out.println("4.Назад");

    }

    private void sortMenu() {
//        SortStrategy strategy;
//        if (type == Bus.class){
//            strategy=new SelectionSortStrategy<Bus>();
//        }
//        if(type == Student.class){
//            strategy=new SelectionSortStrategy<Student>();
//        }
//        if(type == User.class){
//            strategy=new SelectionSortStrategy<User>();
//        }
//        if (strategy!=null){
//            choiceField(strategy);
//        }
    }


    private SortStrategy<?> setAlgorithm(Class<?> type) {
        while (true) {
            printSortMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    return new SelectionSortStrategy<>();
                case 2:
                    return null;
                default:
                    System.out.println("Некорректный ввод");
            }
        }
    }

    private void choiceField(SortStrategy<?> strategy) {
//        while (true) {
//            printFieldsCompare();
//            int choice = getChoice();
//            switch (choice) {
//                case 1:
//                    if (type==Bus.class){
//
//                    }
//
//
//                case 2:
//                case 3:
//
//                default:
//                    System.out.println("Некорректный ввод");
//            }
//        }

    }

    private void printSortMenu(){
        System.out.println("----Выберите способ сортировки----");
        System.out.println("1.Сортировка выбором");
        System.out.println("2.Назад");
    }

    private void printFieldsCompare(){
        System.out.println("----Выберите поле сортировки----");
        if (type==Bus.class){
            System.out.println("1.Номер");
            System.out.println("2.Модель");
            System.out.println("3.Пробег");
        }
        if (type==User.class){
            System.out.println("1.Имя");
            System.out.println("2.Пароль");
            System.out.println("3.Email");
        }
        if (type==Student.class){
            System.out.println("1.Номер группы");
            System.out.println("2.Средний балл");
            System.out.println("3.Номер зачетной книжки");
        }
        System.out.println("4.Назад");
    }
}
