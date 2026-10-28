import java.util.Scanner;

public class Main {

    public static final int EQUIPMENT_COUNT = 5;
    public static final int LOW_STOCK_LIMIT = 5;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        SportsEquipment[] equipment = new SportsEquipment[EQUIPMENT_COUNT];

        for (int i = 0; i < equipment.length; i++) {
            System.out.println("\n=== Інвентар №" + (i + 1) + " ===");

            int categoryType;

            do {
                System.out.println("\nОберіть категорію обладнання:");
                System.out.println("1 — Кардіообладнання");
                System.out.println("2 — Силове обладнання");
                System.out.print("Ваш вибір: ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Помилка: введіть число 1 або 2.");
                    scanner.nextLine();
                    System.out.print("Ваш вибір: ");
                }

                categoryType = scanner.nextInt();
                scanner.nextLine();

                if (categoryType < 1 || categoryType > 2) {
                    System.out.println("Такого варіанта немає. Спробуйте ще раз.");
                }
            } while (categoryType < 1 || categoryType > 2);

            int equipmentType;
            String equipmentName;
            String equipmentCategory;
            double maxSpeed = 0;
            double maxIncline = 0;
            double maxLoad = 0;

            if (categoryType == 1) {
                equipmentCategory = "Кардіообладнання";

                do {
                    System.out.println("\nОберіть вид кардіообладнання:");
                    System.out.println("1 — Бігова доріжка");
                    System.out.println("2 — Інший кардіотренажер");
                    System.out.print("Ваш вибір: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Помилка: введіть число 1 або 2.");
                        scanner.nextLine();
                        System.out.print("Ваш вибір: ");
                    }

                    equipmentType = scanner.nextInt();
                    scanner.nextLine();

                    if (equipmentType < 1 || equipmentType > 2) {
                        System.out.println("Такого варіанта немає. Спробуйте ще раз.");
                    }
                } while (equipmentType < 1 || equipmentType > 2);

                if (equipmentType == 1) {
                    equipmentName = "Бігова доріжка";
                } else {
                    System.out.print("Введіть назву кардіотренажера: ");
                    equipmentName = scanner.nextLine();
                }

                System.out.print("Введіть максимальну швидкість (км/год): ");
                maxSpeed = scanner.nextDouble();
                scanner.nextLine();

                if (equipmentType == 1) {
                    System.out.print("Введіть максимальний кут нахилу (градуси): ");
                    maxIncline = scanner.nextDouble();
                    scanner.nextLine();
                }

            } else {
                equipmentCategory = "Силове обладнання";
                equipmentType = 3;

                System.out.print("Введіть назву силового обладнання: ");
                equipmentName = scanner.nextLine();

                System.out.print("Введіть максимальне навантаження (кг): ");
                maxLoad = scanner.nextDouble();
                scanner.nextLine();
            }

            System.out.print("Введіть кількість інвентарю: ");
            int quantity = scanner.nextInt();

            System.out.print("Введіть ціну за одиницю: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Введіть виробника: ");
            String manufacturer = scanner.nextLine();

            while (price < 0) {
                try {
                    throw new InvalidPriceException(
                            "Ціна не може бути від'ємною.", price);

                } catch (InvalidPriceException e) {
                    System.out.println("Помилка: " + e.getMessage());
                    System.out.println("Некоректна ціна: " + e.getInvalidPrice());
                }

                System.out.print("Введіть правильну ціну (0 або більше): ");
                price = scanner.nextDouble();
                scanner.nextLine();
            }

            if (categoryType == 1 && equipmentType == 1) {
                equipment[i] = new Treadmill(
                        equipmentName,
                        equipmentCategory,
                        quantity,
                        price,
                        manufacturer,
                        maxSpeed,
                        maxIncline);

            } else if (categoryType == 1) {
                equipment[i] = new CardioEquipment(
                        equipmentName,
                        equipmentCategory,
                        quantity,
                        price,
                        manufacturer,
                        maxSpeed);

            } else {
                equipment[i] = new StrengthEquipment(
                        equipmentName,
                        equipmentCategory,
                        quantity,
                        price,
                        manufacturer,
                        maxLoad);
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

        System.out.println("\n=== SWITCH З PATTERN MATCHING ===");

        for (SportsEquipment item : equipment) {
            String description = switch (item) {
                case Treadmill t ->
                    "Бігова доріжка, максимальна швидкість: "
                            + t.getMaxSpeed()
                            + ", максимальний нахил: "
                            + t.getMaxIncline();

                case CardioEquipment c ->
                    "Кардіотренажер, максимальна швидкість: "
                            + c.getMaxSpeed();

                case StrengthEquipment s ->
                    "Силове обладнання, максимальне навантаження: "
                            + s.getMaxLoad();
            };

            System.out.println(description);
        }

        System.out.println("\n=== INSTANCEOF З PATTERN MATCHING ===");

        for (SportsEquipment item : equipment) {
            if (item instanceof Treadmill treadmill) {
                System.out.println(
                        "Бігова доріжка: " + treadmill.getEquipmentName()
                                + ", кут нахилу: " + treadmill.getMaxIncline());

            } else if (item instanceof CardioEquipment cardio) {
                System.out.println(
                        "Кардіотренажер: " + cardio.getEquipmentName()
                                + ", швидкість: " + cardio.getMaxSpeed());

            } else if (item instanceof StrengthEquipment strength) {
                System.out.println(
                        "Силове обладнання: " + strength.getEquipmentName()
                                + ", навантаження: " + strength.getMaxLoad());
            }
        }

        System.out.println("\n=== ПЕРЕВІРКА EQUALS ===");

        SportsEquipment item1 = new Treadmill(
                "Тестова доріжка", "Кардіообладнання",
                5, 20000, "Test", 18, 10);

        SportsEquipment item2 = new Treadmill(
                "Тестова доріжка", "Кардіообладнання",
                5, 20000, "Test", 18, 10);

        System.out.println("item1.equals(item2): " + item1.equals(item2));
        System.out.println("item2.equals(item1): " + item2.equals(item1));
        System.out.println("Однакові hashCode: "
                + (item1.hashCode() == item2.hashCode()));

        StockCheck[] checks = {
                StockCheckFactory.createCheck("low"),
                StockCheckFactory.createCheck("out")
        };

        System.out.println("\n=== ПЕРЕВІРКА ЗАПАСІВ ===");

        for (StockCheck check : checks) {
            int count = 0;

            for (SportsEquipment item : equipment) {
                if (check.check(item)) {
                    count++;
                }
            }

            Reportable report = (Reportable) check;

            System.out.println(
                    report.getReportName() + ": " + count);
        }

        System.out.println("\n=== ПРИКЛАД РЕЗУЛЬТАТУ ПЕРЕВІРКИ ===");

        AbstractStockCheck sampleCheck = new LowStockCheck();
        sampleCheck.printResult(equipment[0]);

        System.out.println("\n=== ПЕРЕВІРКА ПАТЕРНА STRATEGY ===");

        StockChecker checker = new StockChecker(new LowStockCheck());

        SportsEquipment strategyTestItem = equipment[0];

        System.out.println("Товар: " + strategyTestItem);
        System.out.println("Поточна стратегія: " + checker.getDescription());
        System.out.println("Результат: " + checker.check(strategyTestItem));

        checker.setStrategy(new OutOfStockCheck());

        System.out.println("\nЗмінена стратегія: " + checker.getDescription());
        System.out.println("Результат: " + checker.check(strategyTestItem));

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

        System.out.println("\n=== ПОШУК ІНВЕНТАРЮ ЗА НАЗВОЮ ===");

        System.out.print("Введіть назву інвентарю для пошуку: ");
        String searchName = scanner.nextLine();

        boolean found = findEquipment(equipment, searchName);

        if (found) {
            System.out.println("Шуканий інвентар знайдено.");
        } else {
            System.out.println("Інвентар із такою назвою не знайдено.");
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

        System.out.println("\n=== ПЕРЕВІРКА ПАТЕРНА OBSERVER ===");

        SportsEquipment demoItem = new StrengthEquipment(
                "Тестовий інвентар",
                "Тест",
                10,
                100,
                "Demo",
                50.0);

        StockSubject subject = new StockSubject();

        subject.addObserver(new LowStockObserver());
        subject.addObserver(new StockLogObserver());

        System.out.println("Змінюємо кількість тестового інвентарю...");
        subject.changeQuantity(demoItem, 3);

        System.out.println("\n=== ПЕРЕВІРКА ПАТЕРНА ADAPTER ===");

        StockCheck adaptedCheck = new LegacyStockCheckAdapter(
                new LegacyStockChecker());

        SportsEquipment adapterTestItem = new StrengthEquipment(
                "Тестoвий м'яч",
                "М'ячі",
                0,
                500,
                "Demo",
                0.0);

        System.out.println("Перевірка: " + adaptedCheck.getDescription());
        System.out.println("Результат: " + adaptedCheck.check(adapterTestItem));

        scanner.close();
    }

    public static boolean findEquipment(
            SportsEquipment[] equipment,
            String searchName) {

        for (SportsEquipment item : equipment) {
            if (item != null
                    && item.getEquipmentName() != null
                    && item.getEquipmentName().equalsIgnoreCase(searchName.trim())) {
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
