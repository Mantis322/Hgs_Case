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
       for (Date d: passingTime){
           System.out.println(d);
       }

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
}
