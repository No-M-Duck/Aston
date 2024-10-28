package App;

import input.*;
import models.*;
import strategy.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        main.TestStudent();
    }



    private void TestStudent() {
        List<Student> studentsRnd = new ArrayList<>();
        List<Student> studentsFile = new ArrayList<>();
        List<Student> studentsConsole = new ArrayList<>();

        StudentLoader loader = new StudentLoader();
        System.out.println("* * * Random Student * * *");
        studentsRnd = loader.loadRnd(5);
        showList(studentsRnd);
        System.out.println("* * * File Student * * *");
        try {
            // studentsFile = loader.loadFile("students.csv");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        showList(studentsFile);
        /*
        System.out.println("* * * Console Student * * *");
        studentsConsole = loader.loadConsole();
        showList(studentsConsole);
        SelectionSortStrategy<Student> strategy = new SelectionSortStrategy<>();
        System.out.println("* * * Random Student sort by averageScore * * *");
        strategy.sort(studentsRnd, StudentComparator.averageScore());
        showList(studentsRnd);
        System.out.println("* * * Random Student sort by averageScore ignOdd* * *");
        strategy.sort(studentsRnd, StudentComparator.averageScore(true));
        showList(studentsRnd);
        System.out.println("* * * Random User sort by groupNumber * * *");
        //strategy.sort(studentsRnd, StudentComparator.groupNumber());
        showList(studentsRnd);
        System.out.println("* * * Random User sort by groupNumber ignOdd * * *");
        strategy.sort(studentsRnd, StudentComparator.groupNumber(true));
        showList(studentsRnd);
        System.out.println("* * * Random User sort by recordBookNumber ignOdd* * *");
        strategy.sort(studentsRnd, StudentComparator.recordBookNumber());
        showList(studentsRnd);
        System.out.println("* * * Random User sort by recordBookNumber ignOdd* * *");
        strategy.sort(studentsRnd, StudentComparator.recordBookNumber(true));
        showList(studentsRnd);
         */
        Student st = BinarySearcher.findElement(studentsRnd,studentsRnd.get(2),StudentComparator.groupNumber());
        System.out.println(st);
    }

    private void TestUser() {
        List<User> usersFile = new ArrayList<>();
        List<User> usersRnd = new ArrayList<>();
        List<User> usersConsole = new ArrayList<>();

        UserLoader userLoader = new UserLoader();
        System.out.println("* * * Random User * * *");
        usersRnd = userLoader.loadRnd(5);
        showList(usersRnd);
        System.out.println("* * * File User * * *");
        try {
            usersFile = userLoader.loadFile("users.csv");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        showList(usersFile);
        /*
        System.out.println("* * * Console User * * *");
        usersConsole = userLoader.loadConsole();
        showList(usersConsole);
         */
        SelectionSortStrategy<User> strategy = new SelectionSortStrategy<>();
        System.out.println("* * * Random User sort by email * * *");
        strategy.sort(usersRnd, UserComparator.email());
        showList(usersRnd);
        System.out.println("* * * Random User sort by name * * *");
        strategy.sort(usersRnd, UserComparator.name());
        showList(usersRnd);
        System.out.println("* * * Random User sort by password * * *");
        strategy.sort(usersRnd, UserComparator.password());
        showList(usersRnd);


    }

    private void TestBus() {
        List<Bus> busesFile = new ArrayList<>();
        List<Bus> busesRnd = new ArrayList<>();
        List<Bus> busesConsole = new ArrayList<>();

        BusLoader loader = new BusLoader();
        System.out.println("* * * Random Bus * * *");
        busesRnd = loader.loadRnd(5);
        showList(busesRnd);

        System.out.println("* * * File Bus * * *");
        try {
            busesFile = loader.loadFile("buses.csv");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        showList(busesFile);
/*
        System.out.println("* * * Console Bus * * *");
        busesConsole = loader.loadConsole();
        showList(busesConsole);

 */
        SelectionSortStrategy<Bus> strategy = new SelectionSortStrategy<>();
        System.out.println("* * * Random Bus sort by mileage ignOdd * * *");
        strategy.sort(busesRnd, BusComparator.mileage(true));
        showList(busesRnd);
        System.out.println("* * * Random Bus sort by number * * *");
        strategy.sort(busesRnd, BusComparator.number());
        showList(busesRnd);
        System.out.println("* * * Random Bus sort by model * * *");
        strategy.sort(busesRnd, BusComparator.model());
        showList(busesRnd);
        System.out.println("* * * Random Bus sort by mileage * * *");
        strategy.sort(busesRnd, BusComparator.mileage());
        showList(busesRnd);

    }

    private void showList(List elems) {
        elems.stream().forEach(System.out::println);
    }

}