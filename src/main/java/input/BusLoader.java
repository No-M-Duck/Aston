package input;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BusLoader implements Loader<Bus>{
    @Override
    public List<Bus> loadConsole() {
        List<Bus> buses=new ArrayList<>();
        List<String[]> inputArray=new ArrayList<>();
        List<String> errorInput=new ArrayList<>();

        Scanner scan=new Scanner(System.in);

        System.out.println("Для создания User введите данные следующим образом:");
        System.out.println("Номер, Модель, Пробег");
        System.out.println("ВНИМАНИЕ! Ввод происходит до введения пустой строки!");

        while(true){
            String input=scan.nextLine();
            if (input.equals("")){
                break;
            }
            String[] busString=input.split(", ");
            if(busString.length==3){
                inputArray.add(busString);
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

        Validator<Bus> validator=new BusValidator();

        for (String[] elem:inputArray){
            Bus bus=new Bus.BusBuilder().number(elem[0]).model(elem[1]).mileage(Integer.parseInt(elem[2])).build();
            if(validator.isValid(bus)){
                buses.add(bus);
            }
        }
        return buses;
    }
}
