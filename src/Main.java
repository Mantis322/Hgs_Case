import vehicles.Vehicle;

import javax.sound.sampled.AudioFormat;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Vehicle v1 = new Vehicle(1,"Test Owner",new Date(),100,"car");
        Vehicle v2 =new Vehicle(23,"Test Minibus1",new Date(),50,"minibus");
        Vehicle v3 =new Vehicle(555,"Test Bus1",new Date(),10,"bus");

        ArrayList<Vehicle> wqe = new ArrayList<>();
        wqe.add(v1);
        wqe.add(v2);
        wqe.add(v3);

        try {
           FileOutputStream file = new FileOutputStream("TempDb/vehicleDB.txt");
           ObjectOutputStream output = new ObjectOutputStream(file);
           output.writeObject(wqe);


           output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
