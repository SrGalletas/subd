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

import Entities.Family;
import Entities.Expenses;
import Entities.Small_Expenses;
import Entities.Tipe;

import javax.swing.JComboBox;

public class ExpensesForm extends JFrame {	
	public int idSelected;
	private Connection connection = null;

	private JPanel panel;
	private JTextField textFieldName;
	private JTextField textFieldType;
	private JTextField textFieldManufacturer;
	private JTextField textFieldModel;
	private JComboBox comboBoxType;
	private ArrayList<Tipe> tipe;
	private JTextField textFieldMono;
	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @wbp.parser.constructor
	 */
	public ExpensesForm(Connection connection) throws SQLException {
		initialize();
		this.idSelected = -1;
		this.connection = connection;
	
//		Tipe cu = new Tipe();
//		tipe = new ArrayList<>(cu.getTable(connection));
//		comboBoxType.removeAllItems();
//		for (int i = 0; i < tipe.size(); i++) {
//			comboBoxType.addItem("" + tipe.get(i).getType());
//		}
	}
	public ExpensesForm(int id_ex, Connection connection)throws SQLException  {
		initialize();
		this.idSelected = id_ex;
		this.connection = connection;
		Expenses a = new Expenses();
		ArrayList<Expenses> accessories = new ArrayList<>(a.getTable(connection));
		a = null;
		for (int i = 0; i < accessories.size(); i++) {
			if (id_ex == accessories.get(i).getId()) {
				a = accessories.get(i);
				break;
			}
		}
		textFieldType.setText(a.getType());

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
		lblType.setBounds(25, 25, 118, 16);
		getContentPane().add(lblType);
		
		JLabel lblMoney = new JLabel("Money");
		lblMoney.setBounds(23, 67, 106, 16);
		getContentPane().add(lblMoney);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(25, 110, 56, 16);
		getContentPane().add(lblDate);
		
		textFieldType = new JTextField();
		textFieldType.setBounds(139, 22, 116, 22);
		getContentPane().add(textFieldType);
		textFieldType.setColumns(10);
		
		textFieldManufacturer = new JTextField();
		textFieldManufacturer.setBounds(139, 64, 116, 22);
		getContentPane().add(textFieldManufacturer);
		textFieldManufacturer.setColumns(10);
		
		textFieldModel = new JTextField();
		textFieldModel.setBounds(139, 107, 116, 22);
		getContentPane().add(textFieldModel);
		textFieldModel.setColumns(10);
		
		JButton buttonSave = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		buttonSave.setBounds(34, 186, 97, 25);
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Expenses education = null;
				if (textFieldType.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Îøèáêà");
				}
				try {
					education = new Expenses(textFieldType.getText(), Integer.parseInt(textFieldManufacturer.getText()), textFieldModel.getText(),Integer.parseInt(textFieldMono.getText()) );
					if (idSelected < 0) {
						education.addElement(textFieldType.getText(), Integer.parseInt(textFieldManufacturer.getText()), textFieldModel.getText(),Integer.parseInt(textFieldMono.getText()), connection);
					} else {
						education.refreshElement(idSelected,textFieldType.getText(), Integer.parseInt(textFieldManufacturer.getText()), textFieldModel.getText(),Integer.parseInt(textFieldMono.getText()), connection);
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
		
		JLabel lblType_1 = new JLabel("id");
		lblType_1.setBounds(25, 151, 56, 16);
		panel.add(lblType_1);
		
		textFieldMono = new JTextField();
		textFieldMono.setColumns(10);
		textFieldMono.setBounds(139, 149, 116, 22);
		panel.add(textFieldMono);
		
		
		
		
	}
}
