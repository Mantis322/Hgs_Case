package vehicles;

import java.util.Date;

public class Bus extends Vehicle{
    public Bus(int hgs_number, String owner, Date date, int balance) {
        super(hgs_number, owner, date, balance, "bus");
    }
}
