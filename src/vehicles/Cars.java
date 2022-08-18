package vehicles;

import java.util.Date;

public class Cars extends Vehicle{


    public Cars(int hgs_number, String owner, int balance, String type) {
        super(hgs_number, owner,new Date(), balance, type);
    }
}
