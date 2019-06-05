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

import Entities.Tipe;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TipeForm extends JFrame {
	
	public int idSelected;
	private Connection connection = null;

	private JPanel panel;
	private JTextField textFieldName;

	/**
	 * Launch the application.
	 */
	public TipeForm(Connection connection) {
		initialize();
		this.idSelected = -1;
		this.connection = connection;
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public TipeForm(int id_type, Connection connection)throws SQLException  {
		initialize();
		this.idSelected = id_type;
		this.connection = connection;
		Tipe cu = new Tipe();
		ArrayList<Tipe> customer = new ArrayList<>(cu.getTable(connection));
		cu = null;
		for (int i = 0; i < customer.size(); i++) {
			if (id_type == customer.get(i).getId()) {
				cu = customer.get(i);
				break;
			}
		}
		textFieldName.setText(cu.getType());

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
		
		JButton buttonSave = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		buttonSave.setBounds(12, 171, 97, 25);
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tipe customer = null;
				if (textFieldName.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Îøèáêà");
				}
				try {
					customer = new Tipe(textFieldName.getText());
					if (idSelected < 0) {
						customer.addElement(textFieldName.getText(), connection);
					} else {
						customer.refreshElement(idSelected,textFieldName.getText(),  connection);
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
		lblType.setBounds(40, 49, 56, 16);
		panel.add(lblType);
	}
}
