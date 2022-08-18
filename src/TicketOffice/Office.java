package TicketOffice;


import Helper.Helper;
import vehicles.Cars;
import vehicles.Vehicle;


import java.util.ArrayList;
import java.util.Date;

public class Office {
    private Vehicle vehicle;
    static int totalEarn;

    static ArrayList<Vehicle> passingVehicleList = new ArrayList<>();

    public static boolean payment(Vehicle vehicle){
        int payment;
        int car_amount = 2;
        int minibus_amount = 4;
        int bus_amount = 6;
        Date date;



        switch (vehicle.getType()){
            case "car":

                payment = (int) (vehicle.getBalance() - car_amount);
                if (payment<0){
                    vehicle.setBalance(0);
                    Helper.fileWrite();
                    return false;
                }else {
                    vehicle.setBalance(payment);
                    date = new Date();
                    vehicle.setPassingTimeList(date);
                    totalEarn += car_amount;
                    passingVehicleList.add(vehicle);
                    Helper.fileWrite();
                }

                break;
            case "minibus":
                    payment = (int) (vehicle.getBalance()-minibus_amount);
                if (payment<0){
                    vehicle.setBalance(0);
                    Helper.fileWrite();
                    return false;
                }else {
                    vehicle.setBalance(payment);
                    date = new Date();
                    vehicle.setPassingTimeList(date);
                    totalEarn += minibus_amount;
                    passingVehicleList.add(vehicle);
                    Helper.fileWrite();
                }
                break;

            case "bus":
                payment = (int) (vehicle.getBalance()-bus_amount);
                if (payment<0){
                    vehicle.setBalance(0);
                    Helper.fileWrite();
                    return false;
                }else {
                    vehicle.setBalance(payment);
                    date = new Date();
                    vehicle.setPassingTimeList(date);
                    totalEarn += bus_amount;
                    passingVehicleList.add(vehicle);
                    Helper.fileWrite();
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

    public static ArrayList<Vehicle> getPassingVehicleList(){
        return passingVehicleList;
        }

}
