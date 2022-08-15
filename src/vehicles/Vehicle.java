package vehicles;

import java.util.ArrayList;
import java.util.Date;

public class Vehicle {

    private int Hgs_number;
    private String owner;
    private Date date;
    private double balance;
    private String type;

    private ArrayList<Date> passingTime = new ArrayList<>();

    static ArrayList<Vehicle> vehicles = new ArrayList<>();

    public Vehicle(){
        addFirstVehicles();
    }

    public Vehicle(int hgs_number, String owner, Date date, double balance, String type) {
        Hgs_number = hgs_number;
        this.owner = owner;
        this.date = date;
        this.balance = balance;
        this.type = type;
    }

    public int getHgs_number() {
        return Hgs_number;
    }

    public void setHgs_number(int hgs_number) {
        Hgs_number = hgs_number;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList getDate() {


        return passingTime;
    }

    public void setDate(Date date) {
        passingTime.add(date);

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ArrayList<Vehicle> getList(){

        return vehicles;
    }

    public static boolean add(int hgs_number,String owner,int balance, String type){
        for (Vehicle obj : vehicles){
            if (obj.getHgs_number() == hgs_number){
                return false;
            }
        }
        vehicles.add(new Vehicle(hgs_number,owner,new Date(),balance,type));

        return true;
    }

    public void addFirstVehicles(){
        vehicles.add(new Cars(1,"Test Owner",new Date(),100));
        vehicles.add(new Minibus(23,"Test Minibus1",new Date(),50));
        vehicles.add(new Bus(555,"Test Bus1",new Date(),10));
    }

    public static boolean delete(int hgs_number){


        return true;
    }
}
