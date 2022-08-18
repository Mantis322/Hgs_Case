package TicketOffice;

import Helper.Helper;
import com.google.gson.reflect.TypeToken;
import vehicles.Vehicle;


import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import static vehicles.Vehicle.gson;

public class Office {
    private Vehicle vehicle;
     public static int totalEarn;

    static ArrayList<Vehicle> passingVehicleList = new ArrayList<>();

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
                    passingVehicleList.add(vehicle);
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
                    passingVehicleList.add(vehicle);
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
                    passingVehicleList.add(vehicle);
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
            FileWriter writer = new FileWriter("TempDB/passingVehiclelist.json");
            gson.toJson(passingVehicleList,writer);
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
            FileReader reader = new FileReader("TempDB/passingVehiclelist.json");
            Type type = new TypeToken<ArrayList<Vehicle>>(){}.getType();
            passingVehicleList = gson.fromJson(reader,type);
            reader.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return passingVehicleList;
    }

    public static boolean writeTotalEarn(){
        try {
            FileWriter writer = new FileWriter("TempDB/totalEarn.json");
            gson.toJson(totalEarn,writer);
            writer.flush();
            writer.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

}
