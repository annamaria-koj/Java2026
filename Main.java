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

       double discount = 0;
       double finalCost = totalCost;

       if (totalCost >= 5000) {
          discount = totalCost * 0.10;
          finalCost = totalCost - discount;
       }  

       String priceCategory;

       if (price <= 500) {
           priceCategory = "Бюджетний інвентар";
       } else if (price <= 2000) {
           priceCategory = "Інвентар середньої цінової категорії";
       } else {
           priceCategory = "Дорогий інвентар";
       }
       

       System.out.println("\n=== СПОРТИВНИЙ ІНВЕНТАР⚽ ===");
       System.out.printf("Назва: %s%n", equipmentName);
       System.out.printf("Категорія: %s%n", equipmentCategory);
       System.out.printf("Кількість: %d%n", quantity);
       System.out.printf("Ціна за одиницю: %.2f грн%n", price);
       System.out.printf("Цінова категорія: %s%n", priceCategory);
       System.out.printf("Виробник: %s%n", manufacturer);
       System.out.printf("Загальна вартість: %.2f грн%n", totalCost);
       System.out.printf("Знижка: %.2f грн%n", discount);
       System.out.printf("Вартість після знижки: %.2f грн%n", finalCost);

       if (quantity == 0) {
         System.out.println("Інвентар відсутній.");
    } else if (quantity <= 5) {
    System.out.println("Інвентар є в наявності, але його мало.");
    } else {
        System.out.println("Інвентар є в достатній кількості.");
     }

       scanner.close();

    }
}