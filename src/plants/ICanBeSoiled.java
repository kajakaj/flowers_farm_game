package src.plants;

public interface ICanBeSoiled extends IPlant{
    public void soil() throws PlantIsNotPlantedException;
}
