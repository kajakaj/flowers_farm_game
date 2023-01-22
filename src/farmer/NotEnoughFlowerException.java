package src.farmer;

import src.farm.NotEnoughResourcesException;

public class NotEnoughFlowerException extends NotEnoughResourcesException{
    public NotEnoughFlowerException(){
        super("flowers");
    }
}
