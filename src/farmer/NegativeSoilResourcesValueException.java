package src.farmer;

public class NegativeSoilResourcesValueException extends NegativeValueException{
    public NegativeSoilResourcesValueException(){
        super("Soil resources");
    }
}
