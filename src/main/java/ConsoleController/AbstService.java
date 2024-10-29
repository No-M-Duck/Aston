package ConsoleController;

import java.util.*;

public class AbstService<T> implements Service<T>{

    protected static boolean localExit = true;

    protected Scanner scanner = new Scanner(System.in).useLocale(Locale.UK);
    @Override
    public int menuCreate() {
        System.out.println("* * * Создание данных * * *");
        System.out.println("0.Сменить тип данных(Вернуться в главное меню)");
        System.out.println("1.Случайные данные");
        System.out.println("2.Данные из файла");
        System.out.println("3.Задать данные через консоль");
        return 3;
    }

    protected int getAnswer(int menuCount){
        int answer = -1;

        while (true) {
            try {
                System.out.print("Введите номер: ");
                answer = scanner.nextInt();
                if (answer >= 0 && answer <= menuCount) {
                    break;
                } else {
                    System.out.printf("Ошибка: выберите значение от 0 до %d.\n",menuCount);
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число.");
                scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Ошибка: данные для ввода отсутствуют.");
                break;
            }
        }

        return answer;
    }

    @Override
    public int menuFunctions() {
        System.out.println("* * * Сортировка, поиск и вывод * * *");
        System.out.println("0.Сменить тип данных(Вернуться в главное меню)");
        System.out.println("1.Отсортировать данные(по полю класса)");
        System.out.println("2.Поиск элемента");
        System.out.println("3.Показать данные");
        System.out.println("4.Сохранить данные в файл(формат csv)");
        return 4;
    }


    public int menuSortB() {
        System.out.println("* * * Выберете параметр для сортировки * * *");
        System.out.println("0.Вернуться к меню поиска и сортировки");
        System.out.println("1.Сортировка по номеру");
        System.out.println("2.Сортировка по моделе");
        System.out.println("3.Сортировка по пробегу");
        System.out.println("4.Сортировка по пробегу(игнорирование нечетных значений)");
        return 4;
    }
    public int menuSortS() {
        System.out.println("* * * Выберете параметр для сортировки * * *");
        System.out.println("0.Вернуться к меню поиска и сортировки");
        System.out.println("1.Сортировка по группе");
        System.out.println("2.Сортировка по группе(игнорирование нечетных значений)");
        System.out.println("3.Сортировка по баллу");
        System.out.println("4.Сортировка по баллу(игнорирование нечетных значений)");
        System.out.println("5.Сортировка по номеру зачтной книжки");
        System.out.println("6.Сортировка по номеру зачтной книжки(игнорирование нечетных значений)");
        return 6;
    }

    public int menuSortU() {
        System.out.println("* * * Выберете параметр для сортировки * * *");
        System.out.println("0.Вернуться к меню поиска и сортировки");
        System.out.println("1.Сортировка по имени");
        System.out.println("2.Сортировка по email");
        System.out.println("3.Сортировка по паролю");
        return 3;
    }


    public int menuSearchB() {
        System.out.println("* * * Выберете параметр для поиска * * *");
        System.out.println("* * * ВСЕ ОБЪЕКТЫ ТАКЖЕ БУДУТ ОТСОРТИРОВАНЫ ПО ЭТОМУ ПАРАМЕТРУ * * *");
        System.out.println("0.Вернуться к меню поиска и сортировки");
        System.out.println("1.Поиск индекса элемента по номеру");
        System.out.println("2.Поиск индекса элемента по моделе");
        System.out.println("3.Поиск индекса элемента по пробегу");
        System.out.println("4.Поиск элемента по номеру");
        System.out.println("5.Поиск элемента по моделе");
        System.out.println("6.Поиск элемента по пробегу");
        return 6;
    }

    public int menuSearchU() {
        System.out.println("* * * Выберете параметр для поиска * * *");
        System.out.println("* * * ВСЕ ОБЪЕКТЫ ТАКЖЕ БУДУТ ОТСОРТИРОВАНЫ ПО ЭТОМУ ПАРАМЕТРУ * * *");
        System.out.println("0.Вернуться к меню поиска и сортировки");
        System.out.println("1.Поиск индекса элемента по имени");
        System.out.println("2.Поиск индекса элемента по email");
        System.out.println("3.Поиск индекса элемента по паролю");
        System.out.println("4.Поиск элемента по имени");
        System.out.println("5.Поиск элемента по email");
        System.out.println("6.Поиск элемента по паролю");
        return 6;
    }

    public int menuSearchS() {
        System.out.println("* * * Выберете параметр для поиска * * *");
        System.out.println("* * * ВСЕ ОБЪЕКТЫ ТАКЖЕ БУДУТ ОТСОРТИРОВАНЫ ПО ЭТОМУ ПАРАМЕТРУ * * *");
        System.out.println("0.Вернуться к меню поиска и сортировки");
        System.out.println("1.Поиск индекса элемента по группе");
        System.out.println("2.Поиск индекса элемента по баллу");
        System.out.println("3.Поиск индекса элемента по номеру зачетной книжке");
        System.out.println("4.Поиск элемента по группе");
        System.out.println("5.Поиск элемента по баллу");
        System.out.println("6.Поиск элемента по номеру зачетной книжки");
        return 6;
    }
    protected void defChoice() {
        System.out.println("Поздравляю,вы смогли выйти за рамки нашего функционала");
        System.out.println("Но мы вас помали, так что вернитесь на шаг назад и начните заново");
        localExit = false;
    }
    protected boolean checkData(List data) {
        if (!data.isEmpty()) {
            System.out.println("Отлично, вот ваши данные:");
            data.stream().forEach(System.out::println);
            return true;
        } else {
            System.out.println("Кажется с данными возникла проблема.");
            System.out.println("Давайте начнем сначала");
            return false;
        }

    }
}
