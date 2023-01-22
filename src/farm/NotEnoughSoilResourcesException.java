package src.farm;

public class NotEnoughSoilResourcesException extends NotEnoughResourcesException{
    public NotEnoughSoilResourcesException(){
        super("soil resources");
    }
}
