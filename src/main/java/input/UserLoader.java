package input;

import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserLoader implements Loader<User>{
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
}
