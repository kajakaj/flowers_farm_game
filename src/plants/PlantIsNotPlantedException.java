package src.plants;

public class PlantIsNotPlantedException extends Exception{
    public PlantIsNotPlantedException(Flower flower){
        super(flower + " is not planted.");
    }
    
}