package form;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;

import Entities.Family;
import Entities.Housing;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HousingForm extends JFrame {
	
	public int idSelected;
	private Connection connection = null;

	private JPanel panel;
	private JTextField textFieldName;
	private JTextField textFieldPhone;
	private JTextField textFieldPosition;

	/**
	 * Launch the application.
	 */
	public HousingForm(Connection connection) {
		initialize();
		this.idSelected = -1;
		this.connection = connection;
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public HousingForm(int id, Connection connection)throws SQLException  {
		initialize();
		this.idSelected = id;
		this.connection = connection;
		Housing m = new Housing();
		ArrayList<Housing> master = new ArrayList<>(m.getTable(connection));
		m = null;
		for (int i = 0; i < master.size(); i++) {
			if (id == master.get(i).getId()) {
				m = master.get(i);
				break;
			}
		}
		textFieldName.setText(m.getFullName());

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
		
		textFieldName = new JTextField();
		textFieldName.setBounds(143, 46, 116, 22);
		panel.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setBounds(143, 81, 116, 22);
		panel.add(textFieldPhone);
		textFieldPhone.setColumns(10);
		
		JButton buttonSave = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		buttonSave.setBounds(12, 171, 97, 25);
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Housing master = null;
				if (textFieldName.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Îøèáêà");
				}
				try {
					master = new Housing(textFieldName.getText(), Integer.parseInt(textFieldPhone.getText()), textFieldPosition.getText() );
					if (idSelected < 0) {
						master.addElement(textFieldName.getText(), Integer.parseInt(textFieldPhone.getText()), textFieldPosition.getText(), connection);
					} else {
						master.refreshElement(idSelected,textFieldName.getText(), Integer.parseInt(textFieldPhone.getText()), textFieldPosition.getText(), connection);
					}
					setVisible(false);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		panel.add(buttonSave);
		
		JButton buttonCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		buttonCancel.setBounds(143, 171, 97, 25);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		panel.add(buttonCancel);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(12, 49, 84, 16);
		panel.add(lblType);
		
		JLabel lblMoney = new JLabel("Money");
		lblMoney.setBounds(12, 84, 84, 16);
		panel.add(lblMoney);
		
		textFieldPosition = new JTextField();
		textFieldPosition.setBounds(143, 116, 116, 22);
		panel.add(textFieldPosition);
		textFieldPosition.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(12, 113, 84, 16);
		panel.add(lblDate);
	}
}
