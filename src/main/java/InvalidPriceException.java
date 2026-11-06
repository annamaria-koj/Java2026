public class InvalidPriceException extends DomainException
 {

    private double invalidPrice;

    public InvalidPriceException(String message, double invalidPrice) 
    {
        super(message);
        this.invalidPrice = invalidPrice;
    }

    public double getInvalidPrice() 
    {
        return invalidPrice;
    }
}