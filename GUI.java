import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

public class GUI {
	private Client c1; 
	private JFrame frmSmartHomeAppliance;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmSmartHomeAppliance.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
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
		frmSmartHomeAppliance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel lblOrder = new JLabel("Consume:");
		//////////
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Bananas");
		comboBox.addItem("Apples");
		comboBox.addItem("Beverages");
		comboBox.addItem("Water");
		//////////
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem("Bananas");
		comboBox_1.addItem("Apples");
		comboBox_1.addItem("Beverages");
		comboBox_1.addItem("Water");
		///////////
		textField = new JTextField();
		textField.setColumns(10);
		JButton btnConsume = new JButton("Consume");
		btnConsume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
					c1.consume(Order);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		
		JButton btnMaintenance = new JButton("Maintenance and Services");
		btnMaintenance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FIX.main(c1);
			}
		});
		
		JLabel lblOrder_1 = new JLabel("Order:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		

		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Order = "";
				if(comboBox_1.getSelectedItem().toString().equals("Bananas"))
				{
					Order = "MEMORDER " + c1.myProfile.ID + " Ban " + textField_1.getText();	
				}
				if(comboBox_1.getSelectedItem().toString().equals("Apples"))
				{
					Order = "MEMORDER " + c1.myProfile.ID + " App "+ textField_1.getText();	
				}
				if(comboBox_1.getSelectedItem().toString().equals("Water"))
				{
					Order = "MEMORDER " + c1.myProfile.ID + " Wat "+ textField_1.getText();	
				}
				if(comboBox_1.getSelectedItem().toString().equals("Beverages"))
				{
					Order = "MEMORDER " + c1.myProfile.ID + " Bvg "+ textField_1.getText();	
				}
				try {
					c1.Order(Order);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmSmartHomeAppliance.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
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
									.addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(24, Short.MAX_VALUE))
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
					.addGap(49)
					.addComponent(btnMaintenance)
					.addContainerGap(48, Short.MAX_VALUE))
		);
		frmSmartHomeAppliance.getContentPane().setLayout(groupLayout);
	}
}
