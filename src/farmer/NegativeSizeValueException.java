package src.farmer;

public class NegativeSizeValueException extends NegativeValueException{
    public NegativeSizeValueException(){
        super("Field size");
    }
}
