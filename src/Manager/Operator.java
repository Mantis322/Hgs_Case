package Manager;

import TicketOffice.Office;
import com.google.gson.reflect.TypeToken;
import vehicles.Vehicle;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static vehicles.Vehicle.gson;

public class Operator {
    public static int getTotalEarn() {
        try {
            FileReader reader = new FileReader("TempDB/totalEarn.json");
            Type type = new TypeToken<Integer>(){}.getType();
            Office.totalEarn = gson.fromJson(reader,type);
            reader.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return Office.totalEarn;
    }

}
