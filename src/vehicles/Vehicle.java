package vehicles;

import Helper.Helper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;



public class Vehicle implements Serializable {

    private int Hgs_number;
    private String owner;
    private Date date;
    private int balance;
    private String type;

    private ArrayList<Date> passingTimeList = new ArrayList<>();

   public static ArrayList<Vehicle> vehicles = new ArrayList<>();



   public static Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public Vehicle(int hgs_number, String owner, Date date, int balance, String type) {
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

    public Date getDate() {


        return date;
    }

    public void setDate(Date date) {
        this.date = date;

    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Date> getPassingTimeList() {
        return passingTimeList;
    }

    public void setPassingTimeList(Date passingTime) {
        passingTimeList.add(passingTime);
    }

    public static ArrayList<Vehicle> getList() {

        try {
            FileReader reader = new FileReader("TempDB/vehicle.json");
            Type type = new TypeToken<ArrayList<Vehicle>>(){}.getType();
            vehicles = gson.fromJson(reader,type);
            reader.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return vehicles;
    }

    public static boolean add(int hgs_number, String owner, int balance, String type) {

            for (Vehicle obj : vehicles) {
                if (obj.getHgs_number() == hgs_number) {
                    return false;
                }
            }

            vehicles.add(new Vehicle(hgs_number, owner, new Date(), balance, type));

            Helper.fileWrite();

        return true;
    }


    public static boolean delete(int hgs_number) {
        vehicles.removeIf(t -> t.getHgs_number() == hgs_number);

        Helper.fileWrite();


        return true;
    }

    public static boolean Update(int hgs_number, String name, int balance) {


        for (Vehicle obj : vehicles) {
            if (obj.getHgs_number() == hgs_number) {
                obj.setOwner(name);
                obj.setBalance(balance);
            }
        }

        Helper.fileWrite();

        return true;
    }

    public static Vehicle getFetch(int hgs_number){
        Vehicle vc = null;
        for (Vehicle obj : vehicles){
            if (obj.getHgs_number() == hgs_number){
                vc = obj;
            }
        }


        return vc;
    }
}
