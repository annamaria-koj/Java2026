public class PaginationTest {

    public static void main(String[] args) {

        DBConnection db = DBConnection.getInstance();

        try {
            db.initializeDatabase();

            JdbcSportsEquipmentService service =
                    new JdbcSportsEquipmentService();

            int pageSize = 5;
            int totalRecords = service.count();

            int totalPages =
                    (int) Math.ceil((double) totalRecords / pageSize);

            System.out.println("Усього записів: " + totalRecords);
            System.out.println("Сторінок: " + totalPages);

            for (int page = 1; page <= totalPages; page++) {

                System.out.println("\nСторінка " + page + ":");

                for (SportsEquipment equipment :
                        service.findPage(page, pageSize)) {

                    System.out.println(equipment);
                }
            }

        } finally {
            db.close();
        }
    }
}
