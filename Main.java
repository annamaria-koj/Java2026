import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final int EQUIPMENT_COUNT = 5;
        final int LOW_STOCK_LIMIT = 5;

        SportsEquipment[] equipment = new SportsEquipment[EQUIPMENT_COUNT];

        for (int i = 0; i < equipment.length; i++) {

            System.out.println("\n=== Інвентар №" + (i + 1) + " ===");

            System.out.print("Введіть назву інвентарю: ");
            String equipmentName = scanner.nextLine();

            System.out.print("Введіть категорію інвентарю: ");
            String equipmentCategory = scanner.nextLine();

            int quantity;
            double price;

            try {
                System.out.print("Введіть кількість інвентарю: ");
                quantity = scanner.nextInt();

                System.out.print("Введіть ціну за одиницю: ");
                price = scanner.nextDouble();

                scanner.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("Помилка: кількість та ціна повинні бути числами.");
                scanner.nextLine();
                quantity = 0;
                price = 0;
            }

            System.out.print("Введіть виробника: ");
            String manufacturer = scanner.nextLine();

            try {
                if (price < 0) {
                    throw new InvalidPriceException(
                            "Ціна не може бути від'ємною.",
                            price);
                }

                equipment[i] = new SportsEquipment(
                        equipmentName,
                        equipmentCategory,
                        quantity,
                        price,
                        manufacturer);

            } catch (InvalidPriceException e) {
                System.out.println("Помилка: " + e.getMessage());
                System.out.println("Некоректна ціна: " + e.getInvalidPrice());

                equipment[i] = new SportsEquipment(
                        equipmentName,
                        equipmentCategory,
                        quantity,
                        0,
                        manufacturer);
            }

        }

        try {
            System.out.println("\nПеревірка доступу до елемента масиву...");

            SportsEquipment testItem = equipment[equipment.length];

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Помилка: спроба звернутися до елемента за межами масиву.");

        } finally {
            System.out.println("Перевірку масиву завершено.");
        }

        System.out.println("\n=== СПИСОК СПОРТИВНОГО ІНВЕНТАРЮ ===");

        for (SportsEquipment item : equipment) {
            System.out.println(item);
        }

        int lowStockCount = 0;

        for (SportsEquipment item : equipment) {
            if (item.getQuantity() <= LOW_STOCK_LIMIT) {
                lowStockCount++;
            }
        }

        System.out.println("\nКількість позицій з малою кількістю: " + lowStockCount);

        System.out.println("\n=== ДО СОРТУВАННЯ ===");

        for (SportsEquipment item : equipment) {
            System.out.println(item);
        }

        for (int i = 0; i < equipment.length - 1; i++) {

            for (int j = 0; j < equipment.length - 1 - i; j++) {
                if (equipment[j].getPrice() > equipment[j + 1].getPrice()) {

                    SportsEquipment temp = equipment[j];
                    equipment[j] = equipment[j + 1];
                    equipment[j + 1] = temp;
                }
            }
        }

        System.out.println("\n=== ПІСЛЯ СОРТУВАННЯ ЗА ЦІНОЮ ===");

        for (SportsEquipment item : equipment) {
            System.out.println(item);
        } /* метод вивести */

        SportsEquipment searchItem = new SportsEquipment(
                "Баскетбольний м'яч",
                "М'ячі",
                8,
                750,
                "Spalding");

        boolean found = findEquipment(equipment, searchItem);

        if (found) {
            System.out.println("\nШуканий інвентар знайдено.");
        } else {
            System.out.println("\nШуканий інвентар не знайдено.");
        }

        System.out.println("\n=== ПЕРЕВІРКА РІВНЯ 3 ===");

        double testPrice = -100;
        int testQuantity = 5;

        try {
            validateEquipmentData(testPrice, testQuantity);

        } catch (InvalidPriceException e) {
            System.out.println("Rethrow: неправильна ціна: "
                    + e.getInvalidPrice());

        } catch (InsufficientStockException e) {
            System.out.println("Rethrow: недостатня кількість: "
                    + e.getQuantity());

        } catch (DomainException e) {
            System.out.println("Доменна помилка: "
                    + e.getMessage());
        }

        testPrice = 750;
        testQuantity = 0;

        try {
            validateEquipmentData(testPrice, testQuantity);

        } catch (InvalidPriceException e) {
            System.out.println("Rethrow: неправильна ціна: "
                    + e.getInvalidPrice());

        } catch (InsufficientStockException e) {
            System.out.println("Rethrow: недостатня кількість: "
                    + e.getQuantity());

        } catch (DomainException e) {
            System.out.println("Доменна помилка: "
                    + e.getMessage());
        }

        System.out.println("\n=== ПЕРЕВІРКА ІЄРАРХІЇ ВИНЯТКІВ ===");

        DomainException[] errors = {
                new InvalidPriceException(
                        "Некоректна ціна.",
                        -500),

                new InsufficientStockException(
                        "Недостатня кількість інвентарю.",
                        0)
        };

        for (DomainException error : errors) {

            try {
                throw error;

            } catch (DomainException e) {
                System.out.println(
                        "DomainException перехопив: "
                                + e.getMessage());
            }
        }

        scanner.close();
    }

    public static boolean findEquipment(
            SportsEquipment[] equipment,
            SportsEquipment searchItem) {

        for (SportsEquipment item : equipment) {
            if (item.equals(searchItem)) {
                return true;
            }
        }

        return false;
    }

    public static void validateEquipmentData(double price, int quantity)
            throws DomainException {

        try {
            if (price < 0) {
                throw new InvalidPriceException(
                        "Ціна не може бути від'ємною.",
                        price);
            }

            if (quantity <= 0) {
                throw new InsufficientStockException(
                        "Кількість інвентарю повинна бути більшою за 0.",
                        quantity);
            }

        } catch (DomainException e) {
            System.out.println("Перевірка в методі виявила помилку: "
                    + e.getMessage());

            throw e;
        }
    }

}
