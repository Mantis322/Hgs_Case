import Helper.Helper;
import Helper.Config;
import Manager.Operator;
import TicketOffice.Office;
import vehicles.Vehicle;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class View extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_welcoming;
    private JButton btn_logut;
    private JTabbedPane tabpnl_passing_list;
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
    private JScrollPane scrl_vehicle_pass;
    private JTable tbl_vehicle_pass;
    private JPanel pnl_payment;
    private JPanel pnl_vehicle_list;
    private JPanel pnl_passing_list;
    private JButton btn_rmv_passing_list;
    private JPanel pnl_rmv_pass_list;
    private JPanel pnl_total_earn;
    private JLabel fld_total_earn;
    private JLabel fld_payment;

    private DefaultTableModel mdl_vehicle_list;
    private Object[] row_vehicle_list;

    private Vehicle vehicle;

    private JPopupMenu popupMenu;

    private DefaultTableModel mdl_passing_list;

    private Object[] row_passing_list;


    public View() {

        Helper.setLayout();
        add(wrapper);
        setSize(1000, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        setVisible(true);
        setTitle(Config.PROJECT_NAME);

        //Vehicle Table
        popupMenu = new JPopupMenu();
        JMenuItem passVehicle = new JMenuItem("Geçiş Yap");
        JMenuItem passingList = new JMenuItem("Geçişleri Listele");
        popupMenu.add(passVehicle);
        popupMenu.add(passingList);


        passVehicle.addActionListener(e -> {
            int hgs_number = Integer.parseInt(tbl_vehicle_list.getValueAt(tbl_vehicle_list.getSelectedRow(), 0).toString());
            if (Office.payment(Vehicle.getFetch(hgs_number))) {
                JOptionPane.showMessageDialog(null,
                        "Geçiş Yapılmıştır. Yeni Bakiyeniz: " + Vehicle.getFetch(hgs_number).getBalance(),
                        "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                        loadPassingList();
                        loadTotalEarn();
            } else {
                Helper.messageBox("balance");
            }


            loadVehicle();


        });

        passingList.addActionListener(e -> {
            int hgs_number = Integer.parseInt(tbl_vehicle_list.getValueAt(tbl_vehicle_list.getSelectedRow(), 0).toString());
            PassingList pass = new PassingList(Vehicle.getFetch(hgs_number));
        });

        mdl_vehicle_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 3) return false;

                return super.isCellEditable(row, column);
            }
        };
        Object[] col_vehicleList = {"Hgs Numarası", "İsim Soyisim", "Bakiye", "Araç Tipi"};
        row_vehicle_list = new Object[col_vehicleList.length];

        mdl_vehicle_list.setColumnIdentifiers(col_vehicleList);
        tbl_vehicle_list.setModel(mdl_vehicle_list);
        tbl_vehicle_list.setComponentPopupMenu(popupMenu);
        tbl_vehicle_list.getColumnModel().getColumn(0).setMaxWidth(100);
        tbl_vehicle_list.getColumnModel().getColumn(0).setMinWidth(75);

        tbl_vehicle_list.getColumnModel().getColumn(1).setMaxWidth(350);
        tbl_vehicle_list.getColumnModel().getColumn(1).setMinWidth(300);

        tbl_vehicle_list.getColumnModel().getColumn(2).setMaxWidth(100);
        tbl_vehicle_list.getColumnModel().getColumn(2).setMinWidth(75);
        // Passing List
        mdl_passing_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) return false;

                return super.isCellEditable(row, column);
            }
        };
        Object[] col_passing_list = {"Geçen Araçlar ve Zaman Listesi"};
        row_passing_list = new Object[col_passing_list.length];

        mdl_passing_list.setColumnIdentifiers(col_passing_list);
        tbl_vehicle_pass.setModel(mdl_passing_list);
        tbl_vehicle_pass.getTableHeader().setReorderingAllowed(false);

        loadPassingList();
        loadTotalEarn();


        // ## Passing List
        loadVehicle();

        tbl_vehicle_list.getTableHeader().setReorderingAllowed(false);

        tbl_vehicle_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                int hgs_number = (int) tbl_vehicle_list.getValueAt(tbl_vehicle_list.getSelectedRow(), 0);
                fld_vehicle_remove.setText(String.valueOf(hgs_number));
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }

        });

        tbl_vehicle_list.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int hgs_number = Integer.parseInt(tbl_vehicle_list.getValueAt(tbl_vehicle_list.getSelectedRow(), 0).toString());
                String name = tbl_vehicle_list.getValueAt(tbl_vehicle_list.getSelectedRow(), 1).toString();
                int balance = Integer.parseInt(tbl_vehicle_list.getValueAt(tbl_vehicle_list.getSelectedRow(), 2).toString());


                if (Vehicle.Update(hgs_number, name, balance)) {
                    Helper.messageBox("done");
                }

                loadVehicle();
            }


        });

        tbl_vehicle_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selectedRow = tbl_vehicle_list.rowAtPoint(point);
                tbl_vehicle_list.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });

        // ## Vehicle Table


        btn_vehicle_add.addActionListener(e ->

        {
            if (Helper.isFieldEmpty(fld_hgs_number) || Helper.isFieldEmpty(fld_owner_name) || Helper.isFieldEmpty(fld_vehicle_balance)) {
                Helper.messageBox("fill");
            } else {
                int hgs_number = Integer.parseInt(fld_hgs_number.getText());
                String owner = fld_owner_name.getText();
                int balance = Integer.parseInt(fld_vehicle_balance.getText());
                String type = cmb_vehicle_type.getSelectedItem().toString();
                if (Vehicle.add(hgs_number, owner, balance, type)) {
                    Helper.messageBox("done");
                    fld_hgs_number.setText(null);
                    fld_owner_name.setText(null);
                    fld_vehicle_balance.setText(null);
                    cmb_vehicle_type.setSelectedItem("car");
                } else {
                    Helper.messageBox("already");
                }


            }
            loadVehicle();
        });
        btn_vehicle_rmv.addActionListener(e ->

        {
            if (Helper.isFieldEmpty(fld_vehicle_remove)) {
                Helper.messageBox("fill");
            } else {
                if (Helper.comfrim()) {
                    int hgs_number = Integer.parseInt(fld_vehicle_remove.getText().toString());
                    if (Vehicle.delete(hgs_number)) {
                        Helper.messageBox("done");
                        loadVehicle();
                        fld_vehicle_remove.setText(null);
                    } else {
                        Helper.messageBox("error");
                    }
                }
            }

        });
        btn_logut.addActionListener(e ->

        {
            dispose();
        });
        btn_rmv_passing_list.addActionListener(e -> {
           if (Helper.comfrim()){
               for (Vehicle obj : Vehicle.vehicles){
                   obj.getPassingTimeList().clear();
               }
               Office.passingVehicleList = Vehicle.vehicles;
               Office.totalEarn = 0;
               Helper.fileWrite();
               Office.writePassList();
               Office.writeTotalEarn();
               loadVehicle();
               loadPassingList();
               loadTotalEarn();
           }

        });
    }



    public void loadVehicle() {
        DefaultTableModel clear = (DefaultTableModel) tbl_vehicle_list.getModel();
        clear.setRowCount(0);
        int i;
        for (Vehicle obj : Vehicle.getList()) {
            i = 0;
            row_vehicle_list[i++] = obj.getHgs_number();
            row_vehicle_list[i++] = obj.getOwner();
            row_vehicle_list[i++] = obj.getBalance() + " " + "TL";
            row_vehicle_list[i++] = obj.getType();
            mdl_vehicle_list.addRow(row_vehicle_list);
        }

    }

    public void loadPassingList() {
        DefaultTableModel clear = (DefaultTableModel) tbl_vehicle_pass.getModel();
        clear.setRowCount(0);

        String msg = " // HGS Numaralı Araç // ";
        String msg2 = " // Tarihinde Geçiş Yapmıştır.";


            for (Vehicle obj : Office.getPassingList()) {
                for (Date d : obj.getPassingTimeList()) {
                    row_passing_list[0] = obj.getHgs_number() + msg + d + " // " + msg2;
                    mdl_passing_list.addRow(row_passing_list);
                }

            }


    }

    public void loadTotalEarn(){
        fld_total_earn.setText("Toplam Kazanç : " + Operator.getTotalEarn());
    }
}
