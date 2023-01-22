package src.panels;

import java.util.Scanner;

import src.dao.FarmerDao;
import src.dao.FlowersFarmDatabase;
import src.farm.FlowerFarm;
import src.farmer.Farmer;

public class MainPanel {
    static final int INITIAL_MONEY = 200;
    static final int INITIAL_WATER_RESOURCES = 10;
    static final int INITIAL_SOIL_RESOURCES = 0;
    static final int INITIAL_FARM_SIZE = 3;


    public static void farmerPanel(Farmer farmer){
        FarmerPanel fp = new FarmerPanel(farmer);

        String menu = "\n(1) Show farm\n"
                    + "(2) Plant flower\n"
                    + "(3) Water flower\n"
                    + "(4) Soil flower\n"
                    + "(5) Harvest flower\n"
                    + "(6) Buy products\n"
                    + "(7) Sell products\n"
                    + "(0) Exit";

        System.out.println(menu);

        Scanner sc = new Scanner(System.in);
        String msgFromUser = sc.nextLine();

        switch(msgFromUser.strip().toLowerCase()){
            case "0":
                System.out.println("You are back in the main menu.");
                break;
            case "1":
                fp.showResources();
                farmerPanel(farmer);
                break;
            case "2":
               fp.plantFlower();
                farmerPanel(farmer);
                break;
            case "3":
                fp.waterFlower();
                farmerPanel(farmer);
                break;
            case "4":
                fp.soilFlower();
                farmerPanel(farmer);
                break;
            case "5":
                fp.harvestFlower();
                farmerPanel(farmer);
                break;
            case "6":
                fp.buyProducts();
                farmerPanel(farmer);
                break;
            case "7":
                fp.sellProducts();
                farmerPanel(farmer);
                break;
            default:
                System.out.println("Input not recognized.");
                farmerPanel(farmer);
        }

    }
    
    public static void chooseFarmer(){
        FarmerDao fd = new FarmerDao();

        System.out.println("\nTo play enter name of one of the following farmers:");
        for(Farmer farmer: fd.getAllFarmers()){
            System.out.println("* " + farmer);
        }

        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine().strip().toLowerCase();

        Farmer farmer = fd.get(name);
        if(farmer != null) {
            System.out.println("\nHello on " + farmer.getName() + "'s farm!");
            farmerPanel(farmer);
        } else {
            System.out.println("There is no farmer with this name.");
            mainPanel();
        }

    }

    public static void addFarmer(){
        FarmerDao fd = new FarmerDao();

        System.out.println("\nEnter name of the new farmer:");

        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine().strip().toLowerCase();

        FlowerFarm newFarm = new FlowerFarm(INITIAL_FARM_SIZE, INITIAL_WATER_RESOURCES, INITIAL_SOIL_RESOURCES, null);
        Farmer newFarmer = new Farmer(name, INITIAL_MONEY, newFarm);
        if(fd.insert(newFarmer)){
            System.out.println("Farmer added successfully.");
        } else {
            System.out.println("This name is already taken.");
        }

    }

    public static void deleteFarmer(){
        FarmerDao fd = new FarmerDao();

        System.out.println("\nEnter name of one of the following farmers that you want to delete:");
        for(Farmer farmer: fd.getAllFarmers()){
            System.out.println(farmer);
        }

        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine().strip().toLowerCase();

        if(fd.delete(name)) {
            System.out.println("Farmer deleted successfully.");
        } else {
            System.out.println("There is no farmer with this name.");
        }

    }

    public static void mainPanel(){
        String menu = "\n(1) Choose farmer\n"
                    + "(2) Add farmer\n"
                    + "(3) Delete farmer\n"
                    + "(0) Exit";

        System.out.println(menu);

        Scanner sc = new Scanner(System.in);  
        String msgFromUser = sc.nextLine();

            switch(msgFromUser.strip().toLowerCase()){
                case "0":
                    System.out.println("Bye bye!");
                    break;
                case "1":
                    chooseFarmer();
                    mainPanel();
                    break;
                case "2":
                    addFarmer();
                    mainPanel();
                    break;
                case "3":
                    deleteFarmer();
                    mainPanel();
                    break;
                default:
                    System.out.println("Input not recognized.");
                    mainPanel();
            }
    }

    public static void main(String[] args){

        new FlowersFarmDatabase();
       
        System.out.println("Hello on flowers' farm!");
        mainPanel();
    }
}
