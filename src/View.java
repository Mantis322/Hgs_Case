import Helper.Helper;
import vehicles.Vehicle;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class View  extends  JFrame{
    private JPanel wrapper;
    private JPanel pnl_welcoming;
    private JButton btn_logut;
    private JTabbedPane tabpnl_vehicle_list;
    private JScrollPane scrl_vehicle_list;
    private JTable tbl_vehicle_list;
    private JPanel pnl_vehicle_add;
    private JTextField fld_hgs_number;
    private JTextField fld_owner_name;
    private JTextField fld_vehicle_balance;
    private JComboBox cmb_vehicle_type;
    private JButton btn_vehicle_add;
    private JTextField fld_vehicle_remove;
    private JButton btn_vehicle_rmv;

    private DefaultTableModel mdl_vehicle_list;
    private Object[] row_vehicle_list;

    private Vehicle vehicle;

    public View()  {
        Helper.setLayout();
        add(wrapper);
        setSize(1000,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width-getSize().width)/2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height-getSize().height)/2;
        setLocation(x,y);
        setVisible(true);
        setTitle("Hgs Case");

        //Vehicle Table
        mdl_vehicle_list = new DefaultTableModel();
        Object[] col_vehicleList = {"Hgs Numarası" , "İsim Soyisim","Bakiye","Araç Tipi"};
        row_vehicle_list = new Object[col_vehicleList.length];

        mdl_vehicle_list.setColumnIdentifiers(col_vehicleList);
        tbl_vehicle_list.setModel(mdl_vehicle_list);


        loadVehicle();

        tbl_vehicle_list.getTableHeader().setReorderingAllowed(false);

        tbl_vehicle_list.getSelectionModel().addListSelectionListener(e -> {
            try{
                int hgs_number = (int) tbl_vehicle_list.getValueAt(tbl_vehicle_list.getSelectedRow(),0);
                fld_vehicle_remove.setText(String.valueOf(hgs_number));
            }catch (Exception exception){
                System.out.println(exception.getMessage());
            }

        });



        // ## Vehicle Table
        btn_vehicle_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hgs_number) || Helper.isFieldEmpty(fld_owner_name) || Helper.isFieldEmpty(fld_vehicle_balance)){
                JOptionPane.showMessageDialog(null,"Tüm alanları doldunuuz","Mesaj",JOptionPane.INFORMATION_MESSAGE);
            } else {
                int hgs_number = Integer.parseInt(fld_hgs_number.getText());
                String owner = fld_owner_name.getText();
                int balance = Integer.parseInt(fld_vehicle_balance.getText());
                String type = cmb_vehicle_type.getSelectedItem().toString();
                if(Vehicle.add(hgs_number,owner,balance,type)){
                    JOptionPane.showMessageDialog(null,"İşlem Başarılı","Mesaj",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null,"Bu Hgs numarası daha önce alınmış","Mesaj",JOptionPane.INFORMATION_MESSAGE);
                }


            }
            loadVehicle();
        });
    }

    public static void main(String[] args) {
        View view = new View();
        Vehicle vehicle = new Vehicle();


    }

    public void loadVehicle(){
        DefaultTableModel clear = (DefaultTableModel) tbl_vehicle_list.getModel();
        clear.setRowCount(0);
        int i;
        for (Vehicle obj : Vehicle.getList()){
            i = 0;
            row_vehicle_list[i++] = obj.getHgs_number();
            row_vehicle_list[i++] = obj.getOwner();
            row_vehicle_list[i++] = obj.getBalance();
            row_vehicle_list[i++] = obj.getType();
            mdl_vehicle_list.addRow(row_vehicle_list);
        }

    }
}
