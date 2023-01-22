package src.panels;

import java.util.ArrayList;
import java.util.Scanner;

import src.dao.FarmerDao;
import src.dao.FlowerDao;
import src.farm.FlowerFarm;
import src.farm.FlowerNotFoundException;
import src.farm.NotEnoughFieldException;
import src.farm.NotEnoughSoilResourcesException;
import src.farm.NotEnoughWaterResourcesException;
import src.farmer.Farmer;
import src.farmer.NegativeMoneyValueException;
import src.farmer.NegativeQuantityValueEsception;
import src.farmer.NegativeSizeValueException;
import src.farmer.NegativeSoilResourcesValueException;
import src.farmer.NegativeWaterResourcesValueException;
import src.farmer.NotEnoughFlowerException;
import src.farmer.NotEnoughMoneyException;
import src.market.ProductBuy;
import src.market.ProductSell;
import src.plants.Flower;
import src.plants.ICanBeSoiled;
import src.plants.PlantIsNotGrownException;
import src.plants.PlantIsNotPlantedException;
import src.plants.Rose;
import src.plants.Tulip;


public class FarmerPanel {
    private Farmer farmer;

    public FarmerPanel(Farmer farmer){
        this.farmer = farmer;
    }

    public void showResources(){
        System.out.println(farmer.getFlowerFarm().getCurrentStateInfo());
        System.out.println("\nMoney: " + farmer.getMoney());
    }

    public void plantFlower(){
        FlowerFarm farm = farmer.getFlowerFarm();  
        System.out.println("Enter name of the flower you want to plant:");
        System.out.println("Flowers' seeds:");
        System.out.println(farmer.getFlowerFarm().getSeedsInfo());
        
        Scanner sc = new Scanner(System.in);
        String msgFromUser = sc.nextLine();

        Class<? extends Flower> type;
        switch(msgFromUser.strip().toLowerCase()){
            case "tulip":
                type = Tulip.class;
                break;
            case "rose":
                type = Rose.class;
                break;
            default:
                System.out.println("Incorrect flower type name.");
                return;
        }

        try{
            farm.plantFlower(type);
            new FarmerDao().update(farmer);
            System.out.println("Flower planted successfully.");
        } catch (NotEnoughFieldException e) {
            System.out.println("You do not have enough field to plant a flower.");
        } catch (FlowerNotFoundException e) {
            System.out.println("You do not have this flower type seeds.");
        }
    }

    public void waterFlower(){
        FlowerFarm farm = farmer.getFlowerFarm();  
        System.out.println("Enter id of the flower you want to water:");
        System.out.println(farm.getPlantedFlowersInfo());
        
        Scanner sc = new Scanner(System.in);
        String msgFromUser = sc.nextLine();

        try{
            int seedId = Integer.parseInt(msgFromUser);
            Flower flower = farm.getFlower(seedId);
            farm.waterFlower(flower);
            new FarmerDao().update(farmer);
            System.out.println("Flower watered successfully.");
        } catch (FlowerNotFoundException | NumberFormatException | PlantIsNotPlantedException e) {
            System.out.println("There is no planted flower with this id.");
        } catch (NotEnoughWaterResourcesException e) {
            System.out.println("You do not have enough water resources to water this flower.");
        }
    }

    public <T extends ICanBeSoiled> void soilFlower(){
        FlowerFarm farm = farmer.getFlowerFarm();  
        System.out.println("Enter id of the flower you want to soil:");
        System.out.println(farm.getPlantedFlowersInfo());
        
        Scanner sc = new Scanner(System.in);
        String msgFromUser = sc.nextLine();

        try{
            int seedId = Integer.parseInt(msgFromUser);
            T flower = (T) farm.getFlower(seedId);
            farm.soilFlower(flower);
            new FarmerDao().update(farmer);
            System.out.println("Flower soiled successfully.");
        } catch (FlowerNotFoundException | NumberFormatException | PlantIsNotPlantedException | ClassCastException e) {
            System.out.println("There is no planted flower that can be soiled with this id.");
        } catch (NotEnoughSoilResourcesException e) {
            System.out.println("You do not have enough soil resources to water this flower.");
        }
    }

    public void harvestFlower(){
        FlowerFarm farm = farmer.getFlowerFarm();  
        System.out.println("Enter id of the flower you want to harvest:");
        System.out.println(farm.getPlantedFlowersInfo());
        
        Scanner sc = new Scanner(System.in);
        String msgFromUser = sc.nextLine();

        try{
            int seedId = Integer.parseInt(msgFromUser);
            Flower flower = farm.getFlower(seedId);
            farm.harvestFlower(flower);
            new FarmerDao().update(farmer);
            System.out.println("Flower harvested successfully.");
        } catch (FlowerNotFoundException | NumberFormatException | PlantIsNotPlantedException | PlantIsNotGrownException e) {
            System.out.println("There is no flower ready to be harvested with this id.");
        }
    }

    public void buyProducts() {
        System.out.println("\nProducts:");
        for(ProductBuy pBuy: ProductBuy.values()){
            System.out.println("* " + pBuy.name() + ": " + pBuy.getPrice());
        }

        System.out.println("Enter name of the product you want to buy:");
        Scanner sc = new Scanner(System.in);
        String msgFromUserName = sc.nextLine().toUpperCase();

        System.out.println("Enter quantity you want to buy:");
        Scanner sc2 = new Scanner(System.in);
        String msgFromUserQuantity = sc2.nextLine();

        try{
            ProductBuy pBuy = ProductBuy.valueOf(msgFromUserName);
            int quantity = Integer.parseInt(msgFromUserQuantity);
            
            ArrayList<Flower> bougthFlowers = farmer.buyProducts(pBuy, quantity);
            if(bougthFlowers != null){
                for(Flower flower: bougthFlowers){
                    new FlowerDao().insert(flower, farmer.getName());
                }
            }
            new FarmerDao().update(farmer);
            
            System.out.println("You bougth product(s) successfully.");
        } catch (NegativeQuantityValueEsception | NumberFormatException | NegativeSizeValueException | NegativeWaterResourcesValueException | NegativeSoilResourcesValueException e) {
            System.out.println("Quantity must be number bigger than 0.");
        } catch (IllegalArgumentException e) {
            System.out.println("There is no product with that name.");
        } catch (NotEnoughMoneyException e) {
            System.out.println("You do not have enough money.");
        }           
    }

    public void sellProducts(){
        System.out.println("\nProducts:");
        for(ProductSell pSell: ProductSell.values()){
            System.out.println("* " + pSell.name() + ": " + pSell.getPrice());
        }

        System.out.println("\nYour harvested flowers:");
        System.out.println(farmer.getFlowerFarm().getHarvestedFlowersInfo());

        System.out.println("Enter name of the product you want to sell:");
        Scanner sc = new Scanner(System.in);
        String msgFromUserName = sc.nextLine().toUpperCase();

        System.out.println("Enter quantity you want to sell:");
        Scanner sc2 = new Scanner(System.in);
        String msgFromUserQuantity = sc2.nextLine();

        try{
            ProductSell pSell = ProductSell.valueOf(msgFromUserName);
            int quantity = Integer.parseInt(msgFromUserQuantity);
            
            ArrayList<Flower> soldFlowers = farmer.sellProducts(pSell, quantity);
            if(soldFlowers != null){
                for(Flower flower: soldFlowers){
                    new FlowerDao().delete(flower);
                }
            }
            new FarmerDao().update(farmer);
            
            
            System.out.println("You sold product(s) successfully.");
        } catch (NumberFormatException | NegativeMoneyValueException | NegativeQuantityValueEsception e) {
            System.out.println("Quantity must be number bigger than 0.");
        } catch (NotEnoughFlowerException e) {
            System.out.println("You do not have enough flowers.");
        } catch (IllegalArgumentException e) {
            System.out.println("There is no product with that name.");
        }
    }
}
