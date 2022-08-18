package Helper;


import vehicles.Vehicle;
import javax.swing.*;
import java.io.FileWriter;

import static vehicles.Vehicle.gson;


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
            case "balance":
                msg = "Yetersiz Bakiye";
                title = "Hata";
                break;
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

    public static boolean fileWrite(){
       try {
           FileWriter  writer = new FileWriter("TempDB/vehicle.json");
           gson.toJson(Vehicle.vehicles,writer);
           writer.flush();
           writer.close();
       }catch (Exception e){
           System.out.println(e.getMessage());
           return false;
       }
        return true;
    }
}
