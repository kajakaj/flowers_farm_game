package src.plants;

import src.farmer.NegativeValueException;

public class NegativeHydrationValueException extends NegativeValueException{
    public NegativeHydrationValueException(){
        super("Current hydration");
    }
}
