import Manager.Operator;
import TicketOffice.Office;
import vehicles.Bus;
import vehicles.Cars;
import vehicles.Minibus;
import vehicles.Vehicle;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Vehicle car1 = new Cars(1,"Test Owner",new Date(),100);
        Vehicle minibus1 = new Minibus(23,"Test Minibus1",new Date(),50);
        Vehicle bus1 = new Bus(555,"Test Bus1",new Date(),10);

        Office.payment(car1);
        Office.payment(minibus1);
        Office.payment(bus1);

        System.out.println(Operator.getTotalEarn());

    }
}
