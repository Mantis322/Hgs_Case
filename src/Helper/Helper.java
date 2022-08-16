package Helper;


import vehicles.Vehicle;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Helper {

    public static void setLayout(){
        for (UIManager.LookAndFeelInfo a : UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(a.getName())){
                try {
                    UIManager.setLookAndFeel(a.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }

    public static boolean fileWriter(ArrayList vehicleList){
        try {
            FileOutputStream file = new FileOutputStream("TempDb/vehicleDB.txt");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(vehicleList);

            output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }



    public static void messageBox(String message){
        messageboxTR();
        String msg;
        String title;
        switch (message) {
            case "fill":
                msg = "Tüm alanları doldunuuz";
                title = "Mesaj";
                break;
            case "done":
                msg = "İşlem başarılı!";
                title = "Bilgilendirme";
                break;
            case "already":
                msg = "Bu HGS numarası zaten alınmış";
                title = "Hata";
            default:
                msg = "Bir hata meydana geldi";
                title = "Hata";
                break;

        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean comfrim(){
        messageboxTR();
        return JOptionPane.showConfirmDialog(null,"Bu işlemi yapmaktan emin misiniz ? ","Onay",JOptionPane.YES_NO_OPTION)==0;
    }

    public static void messageboxTR(){
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
        UIManager.put("OptionPane.okButtonText", "Tamam");
    }

    public static void clearFile(){
        try {
            new FileWriter("TempDB/vehicleDB.txt", false).close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
