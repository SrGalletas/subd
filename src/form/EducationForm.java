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

import Entities.Education;
import Entities.Small_Expenses;

public class EducationForm extends JFrame {	
	public int idSelected;
	private Connection connection = null;

	private JPanel panel;
	private JTextField textFieldName;
	private JTextField textFieldType;
	private JTextField textFieldManufacturer;
	private JTextField textFieldModel;

	/**
	 * Launch the application.
	 * @wbp.parser.constructor
	 */
	public EducationForm(Connection connection) {
		initialize();
		this.idSelected = -1;
		this.connection = connection;
	}
	
	public EducationForm(int id_education, Connection connection)throws SQLException  {
		initialize();
		this.idSelected = id_education;
		this.connection = connection;
		Education a = new Education();
		ArrayList<Education> accessories = new ArrayList<>(a.getTable(connection));
		a = null;
		for (int i = 0; i < accessories.size(); i++) {
			if (id_education == accessories.get(i).getId()) {
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
		lblType.setBounds(25, 43, 118, 16);
		getContentPane().add(lblType);
		
		JLabel lblMoney = new JLabel("Money");
		lblMoney.setBounds(25, 89, 106, 16);
		getContentPane().add(lblMoney);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(25, 133, 56, 16);
		getContentPane().add(lblDate);
		
		textFieldType = new JTextField();
		textFieldType.setBounds(139, 40, 116, 22);
		getContentPane().add(textFieldType);
		textFieldType.setColumns(10);
		
		textFieldManufacturer = new JTextField();
		textFieldManufacturer.setBounds(139, 86, 116, 22);
		getContentPane().add(textFieldManufacturer);
		textFieldManufacturer.setColumns(10);
		
		textFieldModel = new JTextField();
		textFieldModel.setBounds(139, 130, 116, 22);
		getContentPane().add(textFieldModel);
		textFieldModel.setColumns(10);
		
		JButton buttonSave = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		buttonSave.setBounds(34, 186, 97, 25);
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Education education = null;
				if (textFieldType.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Îøèáêà");
				}
				try {
					education = new Education(textFieldType.getText(), Integer.parseInt(textFieldManufacturer.getText()), textFieldModel.getText() );
					if (idSelected < 0) {
						education.addElement(textFieldType.getText(), Integer.parseInt(textFieldManufacturer.getText()), textFieldModel.getText(), connection);
					} else {
						education.refreshElement(idSelected,textFieldType.getText(), Integer.parseInt(textFieldManufacturer.getText()), textFieldModel.getText(), connection);
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
