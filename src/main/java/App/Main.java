package App;

import input.BusLoader;
import input.UserLoader;
import models.Bus;
import models.User;
import strategy.BusComparator;
import strategy.SelectionSortStrategy;
import strategy.SortStrategy;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.TestUser();
    }

    private void TestUser() {
        List<User> usersFile = new ArrayList<>();
        List<User> usersRnd = new ArrayList<>();
        List<User> usersConsole = new ArrayList<>();

        UserLoader userLoader = new UserLoader();

        usersRnd = userLoader.loadRnd(5);
        showList(usersRnd);

        SortStrategy<User> strategy = new SelectionSortStrategy<>();


    }

    private void TestBus() {
        List<Bus> busesFile = new ArrayList<>();
        List<Bus> busesRnd = new ArrayList<>();
        List<Bus> busesConsole = new ArrayList<>();

        BusLoader loader = new BusLoader();

        busesRnd = loader.loadRnd(5);
        showList(busesRnd);
        try {
            busesFile = loader.loadFile("buses.csv");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        //showList(busesFile);
        System.out.println();
        SelectionSortStrategy<Bus> strategy = new SelectionSortStrategy<>();
        strategy.sort(busesRnd, new BusComparator.SortNumber());
        showList(busesRnd);
    }

    private void showList(List elems) {
        elems.stream().forEach(System.out::println);
    }
}