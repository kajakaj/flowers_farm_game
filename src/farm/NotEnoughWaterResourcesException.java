package src.farm;

public class NotEnoughWaterResourcesException extends NotEnoughResourcesException{
    public NotEnoughWaterResourcesException(){
        super("water resources");
    }
}
