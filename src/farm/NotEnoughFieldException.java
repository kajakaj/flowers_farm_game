package src.farm;

public class NotEnoughFieldException extends NotEnoughResourcesException{
    public NotEnoughFieldException(){
        super("field");
    }
}
