package vehicles;

import java.util.Date;

public class Minibus extends Vehicle{


    public Minibus(int hgs_number, String owner, int balance, String type) {
        super(hgs_number, owner,new Date(), balance, type);
    }
}
