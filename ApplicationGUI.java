import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ApplicationGUI {

    private Client c1;
    private JFrame frmSmartHomeAppliance;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ApplicationGUI window = new ApplicationGUI();
                window.frmSmartHomeAppliance.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     * @wbp.parser.entryPoint
     */
    private ApplicationGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        c1 = new Client();
        frmSmartHomeAppliance = new JFrame();
        frmSmartHomeAppliance.setTitle("Smart Home Appliance");
        frmSmartHomeAppliance.setBounds(100, 100, 391, 455);
        frmSmartHomeAppliance.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel lblOrder = new JLabel("Consume:");
        //////////
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Bananas");
        comboBox.addItem("Apples");
        comboBox.addItem("Beverages");
        comboBox.addItem("Water");
        //////////
        JComboBox<String> comboBox_1 = new JComboBox<>();
        comboBox_1.addItem("Bananas");
        comboBox_1.addItem("Apples");
        comboBox_1.addItem("Beverages");
        comboBox_1.addItem("Water");
        ///////////
        textField = new JTextField();
        textField.setColumns(10);
        JButton btnConsume = new JButton("Consume");
        btnConsume.addActionListener(arg0 -> {
            String Order = "";
            if(comboBox.getSelectedItem().toString().equals("Bananas"))
            {
                Order = "consume Ban " + textField.getText();
            }
            if(comboBox.getSelectedItem().toString().equals("Apples"))
            {
                Order = "consume App " + textField.getText();
            }
            if(comboBox.getSelectedItem().toString().equals("Water"))
            {
                Order = "consume Wat " + textField.getText();
            }
            if(comboBox.getSelectedItem().toString().equals("Beverages"))
            {
                Order = "consume Bvg " + textField.getText();
            }
            try {
                Client.Order(Client.consume(Order));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        JButton btnMaintenance = new JButton("Maintenance and Services");
        btnMaintenance.addActionListener(e -> FIX.main(c1));

        JLabel lblOrder_1 = new JLabel("Order:");

        textField_1 = new JTextField();
        textField_1.setColumns(10);


        JButton btnOrder = new JButton("Order");
        btnOrder.addActionListener(e -> {
            String Order = "";
            if(comboBox_1.getSelectedItem().toString().equals("Bananas"))
            {
                Order = "MEMORDER " + Client.myProfile.ID + " Ban " + textField_1.getText();
            }
            if(comboBox_1.getSelectedItem().toString().equals("Apples"))
            {
                Order = "MEMORDER " + Client.myProfile.ID + " App "+ textField_1.getText();
            }
            if(comboBox_1.getSelectedItem().toString().equals("Water"))
            {
                Order = "MEMORDER " + Client.myProfile.ID + " Wat "+ textField_1.getText();
            }
            if(comboBox_1.getSelectedItem().toString().equals("Beverages"))
            {
                Order = "MEMORDER " + Client.myProfile.ID + " Bvg "+ textField_1.getText();
            }
            try {
                Client.Order(Order);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


        JButton btnNewButton = new JButton("Pay Bill");
        btnNewButton.addActionListener(arg0 -> {
            String Order = "PAY " + Client.myProfile.ID;
            try {
                Client.Order(Order);
                Client.myProfile.accountSummary.clear();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        JButton btnDueBill = new JButton("Due Bill");
        btnDueBill.addActionListener(e -> {
            String Order = "CHECK " + Client.myProfile.ID;
            try {
                Client.Order(Order);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        JComboBox<String> comboBox_2 = new JComboBox<>();
        comboBox_2.addItem("Apples");
        comboBox_2.addItem("Bananas");
        comboBox_2.addItem("Beverages");
        comboBox_2.addItem("Water");

        JTextPane txtpnTest = new JTextPane();
        txtpnTest.setText("Apples, Bananas, Water, Beverages\n" + c1.myProfile.stockThreshold.toString());

        JButton btnSetMinimum = new JButton("Set Minimum");
        btnSetMinimum.addActionListener(e -> {
            if(comboBox_2.getSelectedItem().toString().equals("Bananas"))
            {
                c1.setThreshold("Ban",Integer.parseInt(textField_2.getText()));
            }
            if(comboBox_2.getSelectedItem().toString().equals("Apples"))
            {
                c1.setThreshold("App",Integer.parseInt(textField_2.getText()));
            }
            if(comboBox_2.getSelectedItem().toString().equals("Water"))
            {
                c1.setThreshold("Wat",Integer.parseInt(textField_2.getText()));
            }
            if(comboBox_2.getSelectedItem().toString().equals("Beverages"))
            {
                c1.setThreshold("Bvg",Integer.parseInt(textField_2.getText()));
            }
            txtpnTest.setText("Apples, Bananas, Water, Beverages\n" + c1.myProfile.stockThreshold.toString());
        });

        textField_2 = new JTextField();
        textField_2.setColumns(10);

        JLabel lblCount = new JLabel("Count:");

        JButton btnAccountSummary = new JButton("Account Summary");
        btnAccountSummary.addActionListener(e -> {
            if(c1.myProfile.accountSummary.isEmpty())
                JOptionPane.showMessageDialog(null,"You do not have any order");
            else
                JOptionPane.showMessageDialog(null, c1.myProfile.accountSummary.toString());
        });

        GroupLayout groupLayout = new GroupLayout(frmSmartHomeAppliance.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(14)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addComponent(lblOrder_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18)
                                                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        .addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        .addComponent(btnOrder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addComponent(lblOrder)
                                                                        .addGap(18)
                                                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        .addComponent(btnConsume)))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(btnDueBill)
                                                                .addGap(163)
                                                                .addComponent(btnNewButton))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addGap(5)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(txtpnTest, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                .addComponent(lblCount)
                                                                                .addGap(18)
                                                                                .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(btnSetMinimum))))))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(70)
                                                .addComponent(btnMaintenance, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(130)
                                                .addComponent(btnAccountSummary)))
                                .addContainerGap(54, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblOrder)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnConsume))
                                .addGap(32)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblOrder_1)
                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnOrder))
                                .addGap(31)
                                .addComponent(btnMaintenance)
                                .addGap(18)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnDueBill)
                                        .addComponent(btnNewButton))
                                .addGap(35)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblCount)
                                        .addComponent(btnSetMinimum))
                                .addGap(18)
                                .addComponent(txtpnTest, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                .addGap(36)
                                .addComponent(btnAccountSummary)
                                .addContainerGap(42, Short.MAX_VALUE))
        );
        frmSmartHomeAppliance.getContentPane().setLayout(groupLayout);
    }
}
