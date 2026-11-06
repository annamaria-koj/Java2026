public class InsufficientStockException extends DomainException
{

    private int quantity;

    public InsufficientStockException(String message, int quantity) 
    {
        super(message);
        this.quantity = quantity;
    }

    public int getQuantity() 
    {
        return quantity;
    }
}