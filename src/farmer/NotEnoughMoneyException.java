package src.farmer;

import src.farm.NotEnoughResourcesException;

public class NotEnoughMoneyException extends NotEnoughResourcesException{
    public NotEnoughMoneyException(){
        super("money");
    }
}
