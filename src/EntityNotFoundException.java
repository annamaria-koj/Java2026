public class EntityNotFoundException extends Exception {

    private final long id;

    public EntityNotFoundException(long id) {
        super("Об'єкт з ID " + id + " не знайдено.");
        this.id = id;
    }

    public long getId(){
        return id;
    }
}
