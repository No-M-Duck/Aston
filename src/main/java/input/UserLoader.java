package input;

import Validation.UserValidator;
import Validation.Validator;
import com.github.javafaker.Faker;
import models.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserLoader implements Loader<User>{

    @Override
    public List<User> loadFile(String filePath) throws IllegalArgumentException, IOException {
        List<User> users = new ArrayList<>();
        List<String> data = Files.readAllLines(Path.of(filePath));
        for (String line : data) {
            String[] parsedLine = line.split(",");

            String name = parsedLine[0];
            String password = parsedLine[1];
            String email = parsedLine[2];

            User user = new User.UserBuilder()
                    .name(name)
                    .password(password)
                    .email(email)
                    .build();

            Validator<User> validator = new UserValidator();
            if (validator.isValid(user)) {
                users.add(user);
            }

        }
        return users;
    }

    @Override
    public List<User> loadConsole() {
        List<User> users=new ArrayList<>();
        List<String[]> inputArray=new ArrayList<>();
        List<String> errorInput=new ArrayList<>();

        Scanner scan=new Scanner(System.in);

        System.out.println("Для создания User введите данные следующим образом:");
        System.out.println("Имя, Почта, Пароль");
        System.out.println("ВНИМАНИЕ! Ввод происходит до введения пустой строки!");

        while(true){
            String input=scan.nextLine();
            if (input.equals("")){
                break;
            }
            String[] userString=input.split(", ");
            if(userString.length==3){
                inputArray.add(userString);
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

        Validator<User> validator=new UserValidator();

        for (String[] elem:inputArray){
            User user=new User.UserBuilder().name(elem[0]).email(elem[1]).password(elem[2]).build();
            if(validator.isValid(user)){
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public List<User> loadRnd(int count) {
        Faker faker = Loader.getFaker();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(
                    new User.UserBuilder()
                            .name(faker.name().firstName())
                            .password(faker.internet().password())
                            .email(faker.internet().emailAddress())
                            .build()
            );
        }
        return users;
    }
}
