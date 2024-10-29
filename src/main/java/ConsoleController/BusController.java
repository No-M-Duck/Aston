package ConsoleController;

import Validation.BusValidator;
import input.BusLoader;
import models.Bus;
import output.BusWriter;
import strategy.BinarySearcher;
import strategy.BusComparator;
import strategy.SelectionSortStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BusController extends AbstService<Bus> {

    private static List<Bus> data = new ArrayList<>();


    public int menuBusCreate() {
        int menuCount = super.menuCreate();
        int answer = super.getAnswer(menuCount);
        BusLoader busLoader = new BusLoader();
        switch (answer) {
            case 0:
                return 0;
            case 1:
                System.out.println("Введите количество автобусов");
                int count = super.getAnswer(Integer.MAX_VALUE);
                data = busLoader.loadRnd(count);
                break;

            case 2:
                System.out.println("Введите полное имя файла(example.csv)");
                System.out.println("Доступные расширения файлов: .csv");
                System.out.println("Если ваш файл находится не в директории программы, введите полный путь к файлу");
                String path = "output\\" + scanner.next();
                try {
                    data = busLoader.loadFile(path);
                } catch (NumberFormatException |
                         ArrayIndexOutOfBoundsException|
                         IOException exception) {
                    System.out.println("Мы не смогли найти ваш файл или ваш файл содержит некорректные данные");
                    System.out.println("Попробуйте выполнить несколько предыдущих шагов снова");
                    return menuBusCreate();
                }
                break;
            case 3:
                data = busLoader.loadConsole();
                if(data.isEmpty()) return 0;
                break;
            default:
                defChoice();

        }
        if(!checkData(data)) return 0;
        return 1;
    }



    public void menuBusSort() {
        localExit = true;
        while (localExit ) {
            int menucount = super.menuFunctions();
            int answer = super.getAnswer(menucount);
            switch (answer) {
                case 0:
                    return;
                case 1:
                    menuBusSortField(data);
                    break;
                case 2:
                    menuBusSearch();
                    break;
                case 3:
                    checkData(data);
                    continue;
                case 4: {
                    try {
                        BusWriter writer = new BusWriter();
                        writer.toFile(data);
                    } catch (IOException e) {
                        System.out.println("Ошибка записи файла");
                    }
                    break;
                }
                case 5: {
                    try {
                        BusWriter writer = new BusWriter();
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



    private void menuBusSortField(List<Bus> data) {
        int menucount = super.menuSortB();
        int answer = super.getAnswer(menucount);
        SelectionSortStrategy<Bus> sortStrategy = new SelectionSortStrategy<>();
        switch (answer) {
            case 0:
                return;
            case 1:
                sortStrategy.sort(data, BusComparator.number());
                return;
            case 2:
                sortStrategy.sort(data, BusComparator.model());
                return;
            case 3:
                sortStrategy.sort(data, BusComparator.mileage());
                return;
            case 4:
                sortStrategy.sort(data, BusComparator.mileage(true));
                return;
            default:
                defChoice();
        }
    }
    private void menuBusSearch(){
        int menuCount = super.menuSearchB();
        int answer = super.getAnswer(menuCount);
        int result = -1;
        Bus search = createBusForSearch(answer);
        Bus resultBus = null;
        switch (answer){
            case 0:
                return;
            case 1:
                result = BinarySearcher.findIndex(data,search,BusComparator.number());
                break;
            case 2:
                result = BinarySearcher.findIndex(data,search,BusComparator.model());
                break;
            case 3:
                result = BinarySearcher.findIndex(data,search,BusComparator.mileage());
                break;
            case 4:
                resultBus = BinarySearcher.findElement(data,search,BusComparator.number());
                break;
            case 5:
                resultBus = BinarySearcher.findElement(data,search,BusComparator.model());
                break;
            case 6:
                resultBus = BinarySearcher.findElement(data,search,BusComparator.mileage());
                break;
            default:
                defChoice();
        }
        if(result>-1){
            System.out.println("Мы нашли его. Индекс вашего элемента: "+result+"; позиция в списке: "+(result+1));
            super.foundElement = data.get(result);
        }else if(resultBus!=null){
            System.out.println("Мы нашли его. Ваш элемент: "+resultBus);
            super.foundElement = resultBus;
        }else {
            System.out.println("Ничего не обнаружено.Попробуем снова");
            menuBusSearch();
        }
    }

    private Bus createBusForSearch(int choice){
        BusValidator busValidator = new BusValidator();
        Bus bus = new Bus.BusBuilder().build();
        scanner.nextLine();
        if (choice==0){
            return bus;
        }
        if(choice%3==0){
            System.out.print("Введите пробег: ");
            try {
                int mileage = scanner.nextInt();
                bus = new Bus.BusBuilder().mileage(mileage).build();
            }catch (NoSuchElementException exception){
                System.out.println("Ошибка ввода, а значит...");
            }

            if(busValidator.isMileageValid(bus)){
                return bus;
            }else {
                System.out.println("По вашим данным невозможно найти элемент");
                return bus;
            }
        }
        if(choice%3==2){
            System.out.print("Введите модель: ");
            String result = scanner.nextLine();
            bus = new Bus.BusBuilder().model(result).build();
            if(busValidator.isModelValid(bus)){
                return bus;
            }else {
                System.out.println("По вашим данным невозможно найти элемент");
                return bus;
            }
        }
        if (choice%3==1){
            System.out.println("Введите номер(A123AA12(3)[RU]): ");
            String result = scanner.next();
            bus = new Bus.BusBuilder().number(result).build();
            if(busValidator.isNumberValid(bus)){
                return bus;
            }else {
                System.out.println("По вашим данным невозможно найти элемент");
                return bus;
            }
        }
        return bus;
    }

}
