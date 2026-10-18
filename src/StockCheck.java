public interface StockCheck {
   boolean check(SportsEquipment item);

   default String getDescription() {
       return "Перевірка стану спортивного інвентарю";
   }
}
