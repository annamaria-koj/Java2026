import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final int EQUIPMENT_COUNT = 5;
        final int LOW_STOCK_LIMIT = 5;

        SportsEquipment[] equipment = new SportsEquipment[EQUIPMENT_COUNT];

        // Заповнення масиву
        for (int i = 0; i < equipment.length; i++) {

            System.out.println("\n=== Інвентар №" + (i + 1) + " ===");

            System.out.print("Введіть назву інвентарю: ");
            String equipmentName = scanner.nextLine();

            System.out.print("Введіть категорію інвентарю: ");
            String equipmentCategory = scanner.nextLine();

            System.out.print("Введіть кількість інвентарю: ");
            int quantity = scanner.nextInt();

            System.out.print("Введіть ціну за одиницю: ");
            double price = scanner.nextDouble();

            scanner.nextLine();

            System.out.print("Введіть виробника: ");
            String manufacturer = scanner.nextLine();

            equipment[i] = new SportsEquipment(
                    equipmentName,
                    equipmentCategory,
                    quantity,
                    price,
                    manufacturer);
        }

        // Виведення всього масиву
        System.out.println("\n=== СПИСОК СПОРТИВНОГО ІНВЕНТАРЮ ===");

        for (SportsEquipment item : equipment) {
            System.out.println(item);
        }

        // Підрахунок інвентарю, якого мало
        int lowStockCount = 0;

        for (SportsEquipment item : equipment) {
            if (item.getQuantity() <= LOW_STOCK_LIMIT) {
                lowStockCount++;
            }
        }

        System.out.println("\nКількість позицій з малою кількістю: " + lowStockCount);

        // Виведення масиву до сортування
        System.out.println("\n=== ДО СОРТУВАННЯ ===");

        for (SportsEquipment item : equipment) {
            System.out.println(item);
        }

        // Сортування за ціною від меншої до більшої
        for (int i = 0; i < equipment.length - 1; i++) {

            for (int j = 0; j < equipment.length - 1 - i; j++) {

                if (equipment[j].getPrice() > equipment[j + 1].getPrice()) {

                    SportsEquipment temp = equipment[j];
                    equipment[j] = equipment[j + 1];
                    equipment[j + 1] = temp;
                }
            }
        }

        // Виведення масиву після сортування
        System.out.println("\n=== ПІСЛЯ СОРТУВАННЯ ЗА ЦІНОЮ ===");

        for (SportsEquipment item : equipment) {
            System.out.println(item);
        }

        // Пошук спортивного інвентарю
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
}

