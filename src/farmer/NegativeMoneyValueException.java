package src.farmer;

public class NegativeMoneyValueException extends NegativeValueException{
    public NegativeMoneyValueException(){
        super("Money");
    }
}
