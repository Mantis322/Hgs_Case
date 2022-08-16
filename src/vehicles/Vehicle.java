package vehicles;

import Helper.Helper;

import java.io.*;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Date;

public class Vehicle implements Serializable {

    private int Hgs_number;
    private String owner;
    private Date date;
    private int balance;
    private String type;

    private ArrayList<Date> passingTime = new ArrayList<>();

    static ArrayList<Vehicle> vehicles = new ArrayList<>();


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

    public ArrayList getDate() {


        return passingTime;
    }

    public void setDate(Date date) {
        passingTime.add(date);

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

    public static ArrayList<Vehicle> getList() {
        ArrayList<Vehicle> tempList = new ArrayList<>();

        try {
            FileInputStream fileInput = new FileInputStream("TempDB/vehicleDB.txt");
            ObjectInputStream input = new ObjectInputStream(fileInput);

            ArrayList<Vehicle> vehicle = (ArrayList<Vehicle>) input.readObject();
            while (vehicle != null) {
                for (Vehicle obj : vehicle) {
                    tempList.add(obj);
                    vehicles.add(obj);
                }

                vehicle = (ArrayList<Vehicle>) input.readObject();
            }
            input.close();
            fileInput.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return tempList;
    }

    public static boolean add(int hgs_number, String owner, int balance, String type) {
        for (Vehicle obj : vehicles) {
            if (obj.getHgs_number() == hgs_number) {
                return false;
            }
        }

        vehicles.add(new Vehicle(hgs_number, owner, new Date(), balance, type));
        Helper.fileWriter(vehicles);

        return true;
    }


    public static boolean delete(int hgs_number) {
        vehicles.removeIf(t -> t.getHgs_number() == hgs_number);

        Helper.clearFile();

        Helper.fileWriter(vehicles);
        return true;
    }

    public static boolean Update(int hgs_number, String name, int balance,String type) {


        for (Vehicle obj : vehicles) {
            if (obj.getHgs_number() == hgs_number) {
                vehicles.set(vehicles.indexOf(obj),new Vehicle(hgs_number,name,new Date(),balance,type));
            }
        }

        Helper.clearFile();

        Helper.fileWriter(vehicles);

        return true;
    }
}
