import TicketOffice.Office;
import vehicles.Cars;
import vehicles.Vehicle;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Vehicle car = new Cars(1,"Test Owner",new Date(),100,"otomobil");

        if (Office.payment(car)){
            System.out.println(car.getOwner());
            car.getDate();
        }


    }
}
