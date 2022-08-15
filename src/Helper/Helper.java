package Helper;

import javax.swing.*;

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
}
