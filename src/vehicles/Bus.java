package vehicles;

import java.util.Date;

public class Bus extends Vehicle{

    public Bus(int hgs_number, String owner, int balance, String type) {
        super(hgs_number, owner,new Date(), balance, type);
    }
}
