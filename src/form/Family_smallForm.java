package form;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Entities.Family;
import Entities.Family_small;
import Entities.Small_Expenses;


public class Family_smallForm extends JFrame  {

	private JFrame frame;
	private Connection connection = null;
	public int idSelected;
	private JComboBox comboBoxDevice;
	private JComboBox comboBoxAccessories;
	private ArrayList<Family> family;
	private ArrayList<Small_Expenses> small;

	/**
	 * @wbp.parser.constructor
	 */
	public Family_smallForm(Connection connection) throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		initialize();
		this.connection = connection;
		this.idSelected = -1;
		

		Small_Expenses a = new Small_Expenses();
		small = new ArrayList<>(a.getTable(connection));
		comboBoxAccessories.removeAllItems();
		for (int i = 0; i < small.size(); i++) {
			comboBoxAccessories.addItem("" + small.get(i).getType());
		}
		Family cu = new Family();
		family = new ArrayList<>(cu.getTable(connection));
		comboBoxDevice.removeAllItems();
		for (int i = 0; i < family.size(); i++) {
			comboBoxDevice.addItem("" + family.get(i).getFullName());
		}
	}
	
	public Family_smallForm(int id, Connection connection) throws SQLException {
		
		initialize();
		int did = 0;
		int aid = 0;
		this.connection = connection;
		this.idSelected = id;
		
		Family_small da = new Family_small();
		ArrayList<Family_small> dev_acc = new ArrayList<>(da.getTable(connection));
		da = null;
		for(int i = 0; i < dev_acc.size(); i++){
			if(id == dev_acc.get(i).getAccessoriesId()){
				da = dev_acc.get(i);
				break;
			}
		}
		Family cu = new Family();
		family = new ArrayList<>(cu.getTable(connection));
		comboBoxDevice.removeAllItems();
		for (int i = 0; i < family.size(); i++) {
			comboBoxDevice.addItem("" + family.get(i).getFullName());
			if(da.getAccessoriesId() == family.get(i).getId()){
				did = i;
			}
		}
		Small_Expenses a = new Small_Expenses();
		small = new ArrayList<>(a.getTable(connection));
		comboBoxAccessories.removeAllItems();
		for (int i = 0; i < small.size(); i++) {
			comboBoxAccessories.addItem("" + small.get(i).getType());
			if(da.getDeviceId() == small.get(i).getId()){
				aid = i;
			}
		}
		comboBoxDevice.setSelectedItem(family.get(did).getFullName());
		comboBoxAccessories.setSelectedItem(small.get(aid).getType());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 480, 282);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u041E\u0431\u0440\u0430\u0437\u043E\u0432\u0430\u043D\u0438\u0435");
		lblNewLabel.setBounds(22, 93, 116, 16);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("\u0421\u0435\u043C\u044C\u044F");
		label.setBounds(22, 48, 116, 16);
		panel.add(label);
		
		comboBoxDevice = new JComboBox();
		comboBoxDevice.setBounds(150, 45, 109, 22);
		panel.add(comboBoxDevice);
		
		comboBoxAccessories = new JComboBox();
		comboBoxAccessories.setBounds(150, 90, 109, 22);
		panel.add(comboBoxAccessories);
		
		JButton buttonSave = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		buttonSave.setBounds(102, 192, 97, 25);
		panel.add(buttonSave);
		
		JButton buttonCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		buttonCancel.setBounds(226, 192, 97, 25);
		panel.add(buttonCancel);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Family_small device_accessories = null;
				
				try {
					device_accessories = new Family_small( (comboBoxDevice.getSelectedIndex()),
							(comboBoxAccessories.getSelectedIndex()));
					if (idSelected < 0) {
						device_accessories.addElement(
								family.get(comboBoxDevice.getSelectedIndex()).getId(),
								small.get(comboBoxAccessories.getSelectedIndex())
										.getId(), connection);
					} else {
						device_accessories.refreshElement(idSelected, 
								family.get(comboBoxDevice.getSelectedIndex()).getId(),
								connection);
					}
					setVisible(false);
				} catch (SQLException ex) {
					Logger.getLogger(Family_smallForm.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		});
	}
}
