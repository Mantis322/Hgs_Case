package TicketOffice;


import vehicles.Vehicle;


import java.util.Date;

public class Office {
    private Vehicle vehicle;
    static int totalEarn;

    public static boolean payment(Vehicle vehicle){
        double payment;
        double car_amount = 2;
        double minibus_amount = 4;
        double bus_amount = 6;
        Date date;



        switch (vehicle.getType()){
            case "otomobil":

                payment = vehicle.getBalance()-car_amount;
                if (payment<0){
                    vehicle.setBalance(0);
                    System.out.println("Bakiyeniz yetersiz");
                    return false;
                }else {
                    vehicle.setBalance(payment);
                    date = new Date();
                    vehicle.setDate(date);
                    totalEarn += car_amount;
                }
                System.out.println("Ödeme işleminiz başarılı");
                System.out.println("Yeni bakiyeniz: " + vehicle.getBalance());
                break;
            case "minibüs":
                    payment = vehicle.getBalance()-minibus_amount;
                if (payment<0){
                    vehicle.setBalance(0);
                    System.out.println("Bakiyeniz yetersiz");
                    return false;
                }else {
                    vehicle.setBalance(payment);
                    date = new Date();
                    vehicle.setDate(date);
                    totalEarn += minibus_amount;
                }
                System.out.println("Ödeme işleminiz başarılı");
                System.out.println("Yeni bakiyeniz: " + vehicle.getBalance());
                break;

            case "otobüs":
                payment = vehicle.getBalance()-bus_amount;
                if (payment<0){
                    vehicle.setBalance(0);
                    System.out.println("Bakiyeniz yetersiz");
                    return false;
                }else {
                    vehicle.setBalance(payment);
                    date = new Date();
                    vehicle.setDate(date);
                    totalEarn += bus_amount;
                }
                System.out.println("Ödeme işleminiz başarılı");
                System.out.println("Yeni bakiyeniz: " + vehicle.getBalance());
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
}
