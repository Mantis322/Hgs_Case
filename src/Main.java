import Helper.Helper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vehicles.Vehicle;

import javax.sound.sampled.AudioFormat;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Vehicle v1 = new Vehicle(1,"Test Owner",new Date(),100,"otomobil");
        Vehicle v2 =new Vehicle(23,"Test Minibus1",new Date(),50,"minibüs");
        Vehicle v3 =new Vehicle(555,"Test Bus1",new Date(),10,"otobüs");

        ArrayList<Vehicle> vehicleArrayList = new ArrayList<>();
        vehicleArrayList.add(v1);
        vehicleArrayList.add(v2);
        vehicleArrayList.add(v3);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter  writer = new FileWriter("TempDB/vehicle.json");
            gson.toJson(vehicleArrayList,writer);
            writer.flush();
            writer.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
