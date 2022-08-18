package TicketOffice;

import Helper.Helper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import vehicles.Vehicle;


import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;



public class Office {
    private Vehicle vehicle;
     public static int totalEarn;

   public static ArrayList<Vehicle> passingVehicleList = new ArrayList<>();

    static  Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static boolean payment(Vehicle vehicle){
        int payment;
        int car_amount = 2;
        int minibus_amount = 4;
        int bus_amount = 6;
        Date date;



        switch (vehicle.getType()){
            case "otomobil":

                payment = (int) (vehicle.getBalance() - car_amount);
                if (payment<0){
                    return false;
                }else {
                    vehicle.setBalance(payment);
                    date = new Date();
                    vehicle.setPassingTimeList(date);
                    totalEarn += car_amount;
                    Office.passingVehicleList = Vehicle.vehicles;
                    Helper.fileWrite();
                    writeTotalEarn();
                    writePassList();

                }

                break;
            case "minibüs":
                    payment = (int) (vehicle.getBalance()-minibus_amount);
                if (payment<0){
                    return false;
                }else {
                    vehicle.setBalance(payment);
                    date = new Date();
                    vehicle.setPassingTimeList(date);
                    totalEarn += minibus_amount;
                    Office.passingVehicleList = Vehicle.vehicles;
                    Helper.fileWrite();
                    writeTotalEarn();
                    writePassList();
                }
                break;

            case "otobüs":
                payment = (int) (vehicle.getBalance()-bus_amount);
                if (payment<0){
                    return false;
                }else {
                    vehicle.setBalance(payment);
                    date = new Date();
                    vehicle.setPassingTimeList(date);
                    totalEarn += bus_amount;
                    Office.passingVehicleList = Vehicle.vehicles;
                    Helper.fileWrite();
                    writeTotalEarn();
                    writePassList();
                }
                break;

        }



        return true;
    }

    public static int getTotalEarn() {
        return totalEarn;
    }

    public static void setTotalEarn(int totalEarn) {
        Office.totalEarn = totalEarn;
    }





    public static boolean writePassList(){
        try {
            FileWriter writer = new FileWriter("TempDB/passingList.json");
            gson.toJson(Office.passingVehicleList,writer);
            writer.flush();
            writer.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static ArrayList<Vehicle> getPassingList(){
        try {
            FileReader reader = new FileReader("TempDB/passingList.json");
            Type type = new TypeToken<ArrayList<Vehicle>>(){}.getType();
            Office.passingVehicleList = gson.fromJson(reader,type);
            reader.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return Office.passingVehicleList;
    }

    public static boolean writeTotalEarn(){
        try {
            FileWriter writer = new FileWriter("TempDB/totalEarn.json");
            gson.toJson(Office.totalEarn,writer);
            writer.flush();
            writer.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

}
