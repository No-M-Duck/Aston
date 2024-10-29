package ConsoleController;

import Validation.UserValidator;
import input.UserLoader;
import models.User;
import output.UserWriter;
import strategy.BinarySearcher;
import strategy.UserComparator;
import strategy.SelectionSortStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserController extends AbstService<User> {

    private static List<User> data = new ArrayList<>();

    public int menuUserCreate() {
        int menuCount = super.menuCreate();
        int answer = super.getAnswer(menuCount);
        UserLoader loader = new UserLoader();
        switch (answer) {
            case 0:
                return 0;
            case 1:
                System.out.println("Введите количество пользователей");
                int count = super.getAnswer(Integer.MAX_VALUE);
                data = loader.loadRnd(count);
                break;

            case 2:
                System.out.println("Введите полное имя файла(example.csv)");
                System.out.println("Доступные расширения файлов: .csv");
                System.out.println("Если ваш файл находится не в директории программы, введите полный путь к файлу");
                String path = scanner.next();
                try {
                    data = loader.loadFile(path);
                } catch (NumberFormatException |
                         ArrayIndexOutOfBoundsException|
                         IOException exception) {
                    System.out.println("Мы не смогли найти ваш файл или ваш файл содержит некорректные данные");
                    System.out.println("Попробуйте выполнить несколько предыдущих шагов снова");
                    return menuUserCreate();
                }
                break;
            case 3:
                data = loader.loadConsole();
                if(data.isEmpty()) return 0;
                break;
            default:
                defChoice();

        }
        checkData(data);
        return 1;
    }

    public void menuUserSort() {
        localExit = true;
        while (localExit) {
            int menucount = super.menuFunctions();
            int answer = super.getAnswer(menucount);
            switch (answer) {
                case 0:
                    return;
                case 1:
                    menuUserSortField(data);
                    break;
                case 2:
                    menuUserSearch();
                    break;
                case 3:
                    checkData(data);
                    continue;
                case 4: {
                    UserWriter writer = new UserWriter();
                    writer.toFile(data);
                    break;
                }
                case 5: {
                    UserWriter writer = new UserWriter();
                    writer.toFile(super.foundElement);
                    super.foundElement = null;
                    break;
                }
                default:
                    defChoice();
            }
        }
    }

    private void menuUserSortField(List<User> data) {
        int menucount = super.menuSortU();
        int answer = super.getAnswer(menucount);
        SelectionSortStrategy<User> sortStrategy = new SelectionSortStrategy<>();
        switch (answer) {
            case 0:
                return;
            case 1:
                sortStrategy.sort(data, UserComparator.name());
                return;
            case 2:
                sortStrategy.sort(data, UserComparator.email());
                return;
            case 3:
                sortStrategy.sort(data, UserComparator.password());
                return;
            default:
                defChoice();
        }
    }

    private void menuUserSearch() {
        int menuCount = super.menuSearchU();
        int answer = super.getAnswer(menuCount);
        int result = -1;
        User search = createUserForSearch(answer);
        User resultUser = null;
        switch (answer) {
            case 0:
                return;
            case 1:
                result = BinarySearcher.findIndex(data, search, UserComparator.name());
                break;
            case 2:
                result = BinarySearcher.findIndex(data, search, UserComparator.email());
                break;
            case 3:
                result = BinarySearcher.findIndex(data, search, UserComparator.password());
                break;
            case 4:
                resultUser = BinarySearcher.findElement(data, search, UserComparator.name());
                break;
            case 5:
                resultUser = BinarySearcher.findElement(data, search, UserComparator.email());
                break;
            case 6:
                resultUser = BinarySearcher.findElement(data, search, UserComparator.password());
                break;
            default:
                defChoice();
        }
        if (result > -1) {
            System.out.println("Мы нашли его. Индекс вашего элемента: " + result + "; позиция в списке: " + (result + 1));
            super.foundElement = data.get(result);
        } else if (resultUser != null) {
            System.out.println("Мы нашли его. Ваш элемент: " + resultUser);
            super.foundElement = resultUser;
        } else {
            System.out.println("Ничего не обнаружено.Попробуем снова");
            menuUserSearch();
        }
    }

    private User createUserForSearch(int choice) {
        UserValidator UserValidator = new UserValidator();
        User User = new User.UserBuilder().build();
        scanner.nextLine();
        if (choice == 0) {
            return User;
        }
        if (choice % 3 == 0) {
            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();
            User = new User.UserBuilder().password(password).build();
            if (UserValidator.isPasswordValid(User)) {
                return User;
            } else {
                System.out.println("По вашим данным невозможно найти элемент");
                return User;
            }
        }
        if (choice % 3 == 2) {
            System.out.print("Введите почту: ");
            String result = scanner.nextLine();
            User = new User.UserBuilder().email(result).build();
            if (UserValidator.isEmailValid(User)) {
                return User;
            } else {
                System.out.println("По вашим данным невозможно найти элемент");
                return User;
            }
        }
        if (choice % 3 == 1) {
            System.out.println("Введите имя: ");
            String result = scanner.next();
            User = new User.UserBuilder().name(result).build();
            if (UserValidator.isNameValid(User)) {
                return User;
            } else {
                System.out.println("По вашим данным невозможно найти элемент");
                return User;
            }
        }
        return User;
    }
}
