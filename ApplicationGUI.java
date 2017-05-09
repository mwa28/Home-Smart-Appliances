import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ApplicationGUI {

	private Client c1;
	private JFrame frmSmartHomeAppliance;
	private JTextField textField;
	private JTextField textField_1;

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
		frmSmartHomeAppliance.setBounds(100, 100, 359, 245);
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
                Client.consume(Order);
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
		GroupLayout groupLayout = new GroupLayout(frmSmartHomeAppliance.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addContainerGap()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(lblOrder)
																.addGap(11)
																.addComponent(textField, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
																.addGap(10)
																.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(btnConsume))
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(lblOrder_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																		.addComponent(btnMaintenance, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
																		.addGroup(groupLayout.createSequentialGroup()
																				.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(ComponentPlacement.UNRELATED)
																				.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
																				.addGap(10)
																				.addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))))))
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(20)
												.addComponent(btnDueBill)
												.addPreferredGap(ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
												.addComponent(btnNewButton)))
								.addContainerGap())
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(3)
												.addComponent(lblOrder))
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnConsume)
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGap(29)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblOrder_1)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnOrder)
										.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(31)
								.addComponent(btnMaintenance)
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnNewButton)
										.addComponent(btnDueBill))
								.addContainerGap(25, Short.MAX_VALUE))
		);
		frmSmartHomeAppliance.getContentPane().setLayout(groupLayout);
	}
}
