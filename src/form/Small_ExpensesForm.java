package form;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Entities.Small_Expenses;
import Entities.Housing;

public class Small_ExpensesForm extends JFrame {	
	public int idSelected;
	private Connection connection = null;

	private JPanel panel;

	private JTextField textFieldType;
	private JTextField textFieldManufacturer;

	/**
	 * Launch the application.
	 * @wbp.parser.constructor
	 */
	public Small_ExpensesForm(Connection connection) {
		initialize();
		this.idSelected = -1;
		this.connection = connection;
	}
	
	public Small_ExpensesForm(int id, Connection connection)throws SQLException  {
		initialize();
		this.idSelected = id;
		this.connection = connection;
		Small_Expenses d = new Small_Expenses();
		ArrayList<Small_Expenses> device = new ArrayList<>(d.getTable(connection));
		d = null;
		for (int i = 0; i < device.size(); i++) {
			if (id == device.get(i).getId()) {
				d = device.get(i);
				break;
			}
		}
		textFieldType.setText(d.getType());

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
		
	//	panel.setBounds(0, 0, 310, 253);
	//	getContentPane().add(panel);
	//	panel.setLayout(null);
	//	getContentPane().setLayout(null);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(25, 43, 118, 16);
		getContentPane().add(lblType);
		
		JLabel lblMoney = new JLabel("Money");
		lblMoney.setBounds(25, 89, 106, 16);
		getContentPane().add(lblMoney);
		
		textFieldType = new JTextField();
		textFieldType.setBounds(139, 40, 116, 22);
		getContentPane().add(textFieldType);
		textFieldType.setColumns(10);
		
		textFieldManufacturer = new JTextField();
		textFieldManufacturer.setBounds(139, 86, 116, 22);
		getContentPane().add(textFieldManufacturer);
		textFieldManufacturer.setColumns(10);
		
		JButton buttonSave = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		buttonSave.setBounds(34, 186, 97, 25);
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Small_Expenses device = null;
				if (textFieldType.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Îøèáêà");
				}
				try {
					device = new Small_Expenses(textFieldType.getText(), Integer.parseInt(textFieldManufacturer.getText()));
					if (idSelected < 0) {
						device.addElement(textFieldType.getText(), Integer.parseInt(textFieldManufacturer.getText()), connection);
					} else {
						device.refreshElement(idSelected,textFieldType.getText(), Integer.parseInt(textFieldManufacturer.getText()), connection);
					}
					setVisible(false);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		getContentPane().add(buttonSave);
		
		JButton buttonCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		buttonCancel.setBounds(158, 186, 97, 25);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		getContentPane().add(buttonCancel);
		
		
		
		
	}

}
