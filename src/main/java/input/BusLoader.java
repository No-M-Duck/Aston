package input;


import Validation.*;
import com.github.javafaker.Faker;

import models.Bus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BusLoader implements Loader<Bus>{

    private static final String LETTERS = "АВЕКМНОРСТУХ";

    @Override
    public List<Bus> loadFile(String filePath) throws IllegalArgumentException, IOException {
        List<Bus> buses = new ArrayList<>();
        List<String> data = Files.readAllLines(Path.of(filePath));
        for (String line : data) {
            String[] parsedLine = line.split(",");

            String number = parsedLine[0];
            String model = parsedLine[1];
            int mileage = Integer.parseInt(parsedLine[2]);

            buses.add(new Bus.BusBuilder()
                    .number(number)
                    .model(model)
                    .mileage(mileage)
                    .build());
        }
        return buses;
    }

    @Override
    public List<Bus> loadRnd(int count) {
        Faker faker = Loader.getFaker();
        List<Bus> buses = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            buses.add(
                    new Bus.BusBuilder()
                            .model(faker.company().name() + " " + faker.letterify("Model-???"))
                            .number(generateBusNumber(faker))
                            .mileage(faker.random().nextInt(250000))
                            .build()
            );
        }
        return buses;
    }

    public static String generateBusNumber(Faker faker) {
        char firstLetter = LETTERS.charAt(faker.random().nextInt(LETTERS.length()));
        int digits = faker.number().numberBetween(100, 1000);
        char secondLetter = LETTERS.charAt(faker.random().nextInt(LETTERS.length()));
        char thirdLetter = LETTERS.charAt(faker.random().nextInt(LETTERS.length()));
        int regionCode = faker.number().numberBetween(10, 100);

        return String.format("%c%d%c%c%d", firstLetter, digits, secondLetter, thirdLetter, regionCode);
    }
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
