import Helper.Helper;
import vehicles.Vehicle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class PassingList extends JFrame {

    private JPanel wrapper;
    private JButton btn_close;
    private JScrollPane scrl_passing_list;
    private JTable tbl_passing_list;

    private DefaultTableModel mdl_passing_list;
    private Object[] row_passing_list;

    public PassingList(Vehicle vehicle){
        Helper.setLayout();

        add(wrapper);
        setSize(400,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("Geçiş Listesi");
        setVisible(true);

        mdl_passing_list = new DefaultTableModel();
        Object[] col_passingList = {vehicle.getHgs_number() +" HGS Numaralı Aracın Geçiş Tarih ve Saati"};
        row_passing_list = new Object[col_passingList.length];
        mdl_passing_list.setColumnIdentifiers(col_passingList);
        tbl_passing_list.setModel(mdl_passing_list);
        tbl_passing_list.getTableHeader().setReorderingAllowed(false);


        if(vehicle.getPassingTimeList().isEmpty()){
            row_passing_list[0] = "BU ARAÇ DAHA ÖNCE HİÇ GEÇİŞ YAPMAMIŞTIR.";
            mdl_passing_list.addRow(row_passing_list);

        }else {
            for (Date obj : vehicle.getPassingTimeList()){
                row_passing_list[0] = obj;
                mdl_passing_list.addRow(row_passing_list);
            }
        }



        btn_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


}
