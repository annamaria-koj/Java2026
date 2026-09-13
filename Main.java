import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

       Scanner scanner = new Scanner(System.in);
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

       double totalCost = quantity * price;

       System.out.println("\n=== СПОРТИВНИЙ ІНВЕНТАР⚽ ===");
       System.out.printf("Назва: %s%n", equipmentName);
       System.out.printf("Категорія: %s%n", equipmentCategory);
       System.out.printf("Кількість: %d%n", quantity);
       System.out.printf("Ціна за одиницю: %.2f грн%n", price);
       System.out.printf("Виробник: %s%n", manufacturer);
       System.out.printf("Загальна вартість: %.2f грн%n", totalCost);

       if (quantity > 0) {
        System.out.println("Інвентар є в наявності.");
       }else {
        System.out.println("Інвентар відсутній.");
       }

       scanner.close();

    }
}