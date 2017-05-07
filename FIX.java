import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FIX {

	private JFrame frmMaintenanceAndServices;
	public static Client c;
	/**
	 * Launch the application.
	 */
	public static void main(Client c1) {
		c = c1;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FIX window = new FIX();
					window.frmMaintenanceAndServices.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FIX() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMaintenanceAndServices = new JFrame();
		frmMaintenanceAndServices.setTitle("Maintenance and Services");
		frmMaintenanceAndServices.setBounds(100, 100, 400, 168);
		
		JButton btnFixFridgeCompressor = new JButton("Fix Fridge Compressor");
		btnFixFridgeCompressor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.Order("FIX " + c.myProfile.ID.toString() + " Fdg CompressorFix");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnFixOvenEye = new JButton("Fix Oven Door ");
		btnFixOvenEye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.Order("FIX " + c.myProfile.ID.toString() + " Ovn DoorFix");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton button_1 = new JButton("Oven Gas Change");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.Order("FIX " + c.myProfile.ID.toString() + " Ovn GasChange");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnGasGasChange = new JButton("Gas Gas Change");
		btnGasGasChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.Order("FIX " + c.myProfile.ID.toString() + " Gas GasChange");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnGasEyeFix = new JButton("Fix Gas Eye");
		btnGasEyeFix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.Order("FIX " + c.myProfile.ID.toString() + " Gas EyeFix");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton button = new JButton("Fridge Door Cleaning");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.Order("FIX " + c.myProfile.ID.toString() + " Fdg DoorCleaning");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmMaintenanceAndServices.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnFixFridgeCompressor)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnGasEyeFix, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnGasGasChange, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnFixOvenEye, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addContainerGap(79, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnFixFridgeCompressor)
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnFixOvenEye)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGasGasChange, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGasEyeFix))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		frmMaintenanceAndServices.getContentPane().setLayout(groupLayout);
	}

}
