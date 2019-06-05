package form;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Housing;
import Entities.Small_Expenses;
import Entities.Family;
import Entities.Travel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class TravelForm extends JFrame {

	private Connection connection = null;
	public int idSelected;
	private JPanel contentPane;

	private JPanel panel;
	private JTextField textFieldBreakdown;
	private JTextField textFieldDate;
//	private ArrayList<Device> device;
//	private ArrayList<Family> customer;
//	private ArrayList<Housing> master;
	private JButton buttonSave;
	private JButton buttonCancel;



	public TravelForm(Connection connection) throws SQLException  {
		initialize();
		this.connection = connection;
		this.idSelected = -1;
		
//		Family cu = new Family();
//		customer = new ArrayList<>(cu.getTable(connection));
//		comboBoxCustomer.removeAllItems();
//		for (int i = 0; i < customer.size(); i++) {
//			comboBoxCustomer.addItem("" + customer.get(i).getFullName());
//		}
//		Housing m = new Housing();
//		master = new ArrayList<>(m.getTable(connection));
//		comboBoxMaster.removeAllItems();
//		for (int i = 0; i < master.size(); i++) {
//			comboBoxMaster.addItem("" + master.get(i).getFullName());
//		}
//		Device d = new Device();
//		device = new ArrayList<>(d.getTable(connection));
//		comboBoxDevice.removeAllItems();
//		for (int i = 0; i < device.size(); i++) {
//			comboBoxDevice.addItem("" + device.get(i).getType());
//		}
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public TravelForm(int id, Connection connection) throws SQLException  {
		initialize();
		
//		int cid = 0;
//		int mid = 0;
//		int did = 0;
		this.connection = connection;
		this.idSelected = id;
		Travel o = new Travel();
		ArrayList<Travel >order = new ArrayList<>(o.getTable(connection));
		o = null;
		for (int i = 0; i < order.size(); i++) {
			if (id == order.get(i).getId()) {
				o = order.get(i);
				break;
			}
		}
		
//		Family cu = new Family();
//		customer = new ArrayList<>(cu.getTable(connection));
//		comboBoxCustomer.removeAllItems();
//		for (int i = 0; i < customer.size(); i++) {
//			comboBoxCustomer.addItem("" + customer.get(i).getFullName());
//			if (o.getCustomerid() == customer.get(i).getId()) {
//				cid = i;
//			}
//		}
//		Housing m = new Housing();
//		master = new ArrayList<>(m.getTable(connection));
//		comboBoxMaster.removeAllItems();
//		for (int i = 0; i < master.size(); i++) {
//			comboBoxMaster.addItem("" + master.get(i).getFullName());
//			if (o.getMasterid() == master.get(i).getId()) {
//				mid = i;
//			}
//		}
//		Device d = new Device();
//		device = new ArrayList<>(d.getTable(connection));
//		comboBoxDevice.removeAllItems();
//		for (int i = 0; i < device.size(); i++) {
//			comboBoxDevice.addItem("" + device.get(i).getType());
//			if (o.getDeviceid() == device.get(i).getId()) {
//				did = i;
//			}
//
//		}
		textFieldBreakdown.setText(o.getBreakdown());
		//textFieldDate.setText(o.getDate());
//		textFieldSum.setText(o.getSum());
//		comboBoxCustomer.setSelectedItem(customer.get(cid).getFullName());
//		comboBoxMaster.setSelectedItem(master.get(mid).getFullName());
//		comboBoxDevice.setSelectedItem(device.get(did).getType());

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		setContentPane(panel);
		getContentPane().setLayout(null);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(74, 30, 90, 16);
		getContentPane().add(lblType);
		
		JLabel lblMoney = new JLabel("Money");
		lblMoney.setBounds(69, 74, 56, 16);
		getContentPane().add(lblMoney);
		
		textFieldBreakdown = new JTextField();
		textFieldBreakdown.setBounds(174, 27, 116, 22);
		getContentPane().add(textFieldBreakdown);
		textFieldBreakdown.setColumns(10);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(174, 71, 116, 22);
		getContentPane().add(textFieldDate);
		textFieldDate.setColumns(10);
		
		buttonSave = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		buttonSave.setBounds(41, 187, 97, 25);
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Travel order = null;
				if (textFieldBreakdown.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Îøèáêà");
				}
				try {
					order = new Travel(textFieldBreakdown.getText(),Integer.parseInt(textFieldDate.getText()));
					if (idSelected < 0) {
						order.addElement(textFieldBreakdown.getText(), Integer.parseInt(textFieldDate
								.getText()), connection);
					} else {
						order.refreshElement(idSelected,textFieldBreakdown.getText(),Integer.parseInt(textFieldDate.getText()), connection);
					}
					setVisible(false);
				} catch (SQLException ex) {
					Logger.getLogger(TravelForm.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		});

		getContentPane().add(buttonSave);
		
		buttonCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		buttonCancel.setBounds(234, 187, 97, 25);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		getContentPane().add(buttonCancel);
	}

}
