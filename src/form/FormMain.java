package form;

import java.awt.EventQueue;

import javafx.scene.control.ComboBox;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;








import com.sun.glass.events.WindowEvent;

import Entities.Expenses;
import Entities.Family;
import Entities.Family_expenses;
import Entities.Family_small;
import Entities.Family_travel;
import Entities.Tipe;
import Entities.Travel;
import Entities.Housing;
import Entities.Small_Expenses;
import Entities.Education;
import Entities.Family_education;
import Entities.Family_housing;
import form.FormNewConnection;


public class FormMain {
	JComboBox ComboBox;
	private JFrame frame;
	private JTable table;
	Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMain window = new FormMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//, "Семья-образование" 
		String[] str = { "Семья","Семья-расходы","Расходы", "Путешествие", "Домашние расходы", "Мелкие расходы", "Образование", "Семья-образование", "Семья-домашнее", "Семья-путешествие","Семья-мелкое", "Тип"};
		frame = new JFrame();
		frame.setBounds(100, 100, 679, 312);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

	
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menuConnection = new JMenu("\u041F\u043E\u0434\u043A\u043B\u044E\u0447\u0435\u043D\u0438\u0435");
		menuBar.add(menuConnection);
		
		JMenuItem menuItemNew = new JMenuItem("\u041D\u043E\u0432\u043E\u0435 \u043F\u043E\u0434\u043A\u043B\u044E\u0447\u0435\u043D\u0438\u0435");
		menuItemNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormNewConnection form = new FormNewConnection(frame);
				connection = form.getConnection();
				if (connection != null) {
					
				}
			}
		});
		menuConnection.add(menuItemNew);
		
		
		JMenuItem menuItemShutdown = new JMenuItem("\u041E\u0442\u043A\u043B\u044E\u0447\u0435\u043D\u0438\u0435");
		menuItemShutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Disconnect();
				for( int i=0; i< table.getRowCount(); i++){
					
				}
			}
		});
		
		menuConnection.add(menuItemShutdown);
		
		JMenuItem menuItemExit = new JMenuItem("\u0412\u044B\u0445\u043E\u0434");
		menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuConnection.add(menuItemExit);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 661, 250);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setBounds(12, 13, 523, 218);
		panel.add(table);
		
		ComboBox = new JComboBox(str);
		ComboBox.setBounds(547, 9, 102, 22);
		ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});
		panel.add(ComboBox);
		
		JButton buttonAdd = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		buttonAdd.setBounds(547, 61, 97, 25);
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (connection != null) {
					switch ((String) ComboBox.getSelectedItem()) {
					case "Семья":
						try {
							FamilyForm form1 = new FamilyForm(connection);
							form1.setVisible(true);
							Family cu = new Family();
							table.setModel(cu.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Расходы":
						try {
							ExpensesForm form11 = new ExpensesForm(connection);
							form11.setVisible(true);
							Expenses cu = new Expenses();
							table.setModel(cu.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Семья-расходы":
						try {
							Family_expensesForm form12 = new Family_expensesForm(connection);
							form12.setVisible(true);
							Family_expenses cu = new Family_expenses();
							table.setModel(cu.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Путешествие":
						try {
							TravelForm form2 = new TravelForm(connection);
							form2.setVisible(true);
							Travel or = new Travel();
							table.setModel(or.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Домашние расходы":
						try {
							HousingForm form3 = new HousingForm(connection);
							form3.setVisible(true);
							Housing m = new Housing();
							table.setModel(m.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Мелкие расходы":
						try {
							Small_ExpensesForm form4 = new Small_ExpensesForm(connection);
							form4.setVisible(true);
							Small_Expenses d = new Small_Expenses();
							table.setModel(d.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Образование":
						try {
							EducationForm form5 = new EducationForm(connection);
							form5.setVisible(true);
							Education ac = new Education();
							table.setModel(ac.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Семья-образование":
						try {
							Family_educationForm form6 = new Family_educationForm(connection);
							form6.setVisible(true);
							Family_education da = new Family_education();
							table.setModel(da.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Семья-домашнее":
						try {
							Family_housingForm form7 = new Family_housingForm(connection);
							form7.setVisible(true);
							Family_housing da = new Family_housing();
							table.setModel(da.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Семья-путешествие":
						try {
							Family_travelForm form8 = new Family_travelForm(connection);
							form8.setVisible(true);
							Family_travel da = new Family_travel();
							table.setModel(da.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Семья-мелкое":
						try {
							Family_smallForm form9 = new Family_smallForm(connection);
							form9.setVisible(true);
							Family_small da = new Family_small();
							table.setModel(da.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Тип":
						try {
							TipeForm form10 = new TipeForm(connection);
							form10.setVisible(true);
							Tipe cu = new Tipe();
							table.setModel(cu.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					}
				}
			}
		});
		panel.add(buttonAdd);
		
		JButton buttonDel = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		buttonDel.setBounds(547, 99, 97, 25);
		buttonDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() >= 0 && connection != null) {
					int idEl = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					switch ((String) ComboBox.getSelectedItem()) {
					case "Семья":
						try {
							Family cu = new Family();
							cu.removeElement(idEl, connection);
							table.setModel(cu.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Семья-расходы":
						try {
							Family_expenses cu = new Family_expenses();
							cu.removeElement(idEl, connection);
							table.setModel(cu.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Расходы":
						try {
							Expenses cu = new Expenses();
							cu.removeElement(idEl, connection);
							table.setModel(cu.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Путешествие":
						try {
							Travel or = new Travel();
							or.removeElement(idEl, connection);
							table.setModel(or.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Домашние расходы":
						try {
							Housing m = new Housing();
							m.removeElement(idEl, connection);
							table.setModel(m.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Мелкие расходы":
						try {
							Small_Expenses d = new Small_Expenses();
							d.removeElement(idEl, connection);
							table.setModel(d.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Образование":
						try {
							Education ac = new Education();
							ac.removeElement(idEl, connection);
							table.setModel(ac.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Семья-образование":
						try {
							Family_education da = new Family_education();
							da.removeElement(idEl, connection);
							table.setModel(da.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Семья-домашнее":
						try {
							Family_housing da = new Family_housing();
							da.removeElement(idEl, connection);
							table.setModel(da.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Семья-путешествие":
						try {
							Family_travel da = new Family_travel();
							da.removeElement(idEl, connection);
							table.setModel(da.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Семья-мелкое":
						try {
							Family_small da = new Family_small();
							da.removeElement(idEl, connection);
							table.setModel(da.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Тип":
						try {
							Tipe cu = new Tipe();
							cu.removeElement(idEl, connection);
							table.setModel(cu.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					}
				} else if (connection != null) {
					JOptionPane.showMessageDialog(null, "Выберите элемент");
				}
			}
		});
		panel.add(buttonDel);
		
		JButton buttonCh = new JButton("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C");
		buttonCh.setBounds(547, 137, 97, 25);
		buttonCh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() >= 0 && connection != null) {
					int idEl = (table.getSelectedRow());
					switch ((String) ComboBox.getSelectedItem()) {
					case "Семья":
						try {
							Family cu = new Family();
							int i = cu.getTable(connection).get(idEl-1).getId();
							FamilyForm form1 = new FamilyForm(i, connection);
							form1.setVisible(true);
							Family g = new Family();
							table.setModel(g.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Расходы":
						try {
							Expenses cu = new Expenses();
							int i = cu.getTable(connection).get(idEl-1).getId();
							ExpensesForm form11 = new ExpensesForm(i, connection);
							form11.setVisible(true);
							Expenses g = new Expenses();
							table.setModel(g.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Семья-расходы":
						try {
							Family_expenses da = new Family_expenses();
							int i = da.getTable(connection).get(idEl-1).getAccessoriesId();
							Family_expensesForm form12 = new Family_expensesForm(i, connection);
							form12.setSize(400, 400);
							form12.setVisible(true);
							Family_expenses ad = new Family_expenses();
							table.setModel(ad.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Путешествие":
						try {
							Travel or = new Travel();
							int i = or.getTable(connection).get(idEl-1).getId();
							TravelForm form2 = new TravelForm(i, connection);
							form2.setVisible(true);
							Travel o = new Travel();
							table.setModel(o.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Домашние расходы":
						try {
							Housing m = new Housing();
							int i = m.getTable(connection).get(idEl-1).getId();
							HousingForm form3 = new HousingForm(i, connection);
							form3.setVisible(true);
							Housing ma = new Housing();
							table.setModel(ma.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Мелкие расходы":
						try {
							Small_Expenses d = new Small_Expenses();
							int i = d.getTable(connection).get(idEl-1).getId();
							Small_ExpensesForm form4 = new Small_ExpensesForm(i, connection);
							form4.setVisible(true);
							Small_Expenses de = new Small_Expenses();
							table.setModel(de.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Образование":
						try {
							Education ac = new Education();
							int i = ac.getTable(connection).get(idEl-1).getId();
							EducationForm form5 = new EducationForm(i, connection);
							form5.setVisible(true);
							Education a = new Education();
							table.setModel(a.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Семья-образование":
						try {
							Family_education da = new Family_education();
							int i = da.getTable(connection).get(idEl-1).getAccessoriesId();
							Family_educationForm form6 = new Family_educationForm(i, connection);
							form6.setSize(400, 400);
							form6.setVisible(true);
							Family_education ad = new Family_education();
							table.setModel(ad.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Семья-домашнее":
						try {
							Family_housing da = new Family_housing();
							int i = da.getTable(connection).get(idEl-1).getAccessoriesId();
							Family_housingForm form7 = new Family_housingForm(i, connection);
							form7.setSize(400, 400);
							form7.setVisible(true);
							Family_housing ad = new Family_housing();
							table.setModel(ad.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Семья-путешествие":
						try {
							Family_travel da = new Family_travel();
							int i = da.getTable(connection).get(idEl-1).getAccessoriesId();
							Family_travelForm form8 = new Family_travelForm(i, connection);
							form8.setSize(400, 400);
							form8.setVisible(true);
							Family_travel ad = new Family_travel();
							table.setModel(ad.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Семья-мелкое":
						try {
							Family_small da = new Family_small();
							int i = da.getTable(connection).get(idEl-1).getAccessoriesId();
							Family_smallForm form9 = new Family_smallForm(i, connection);
							form9.setSize(400, 400);
							form9.setVisible(true);
							Family_small ad = new Family_small();
							table.setModel(ad.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Тип":
						try {
							Tipe cu = new Tipe();
							int i = cu.getTable(connection).get(idEl-1).getId();
							TipeForm form10 = new TipeForm(i, connection);
							form10.setVisible(true);
							Tipe g = new Tipe();
							table.setModel(g.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					}
				} else if (connection != null) {
					JOptionPane.showMessageDialog(null, "Выберите элемент");
				}
			}
		});
		
		panel.add(buttonCh);
		
	
				
		
		JButton buttonUpd = new JButton("\u041E\u0431\u043D\u043E\u0432\u0438\u0442\u044C");
		buttonUpd.setBounds(547, 175, 97, 25);
		buttonUpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});
		
		panel.add(buttonUpd);
		
		

		
		}
		private void Disconnect() {
			if (connection != null) {
				connection = null;
				JOptionPane.showMessageDialog(null, "Соеденение закрыто");
			}
		}

		private void refresh() {
			if (connection != null) {
				switch ((String) ComboBox.getSelectedItem()) {
				case "Семья":
					
					try {
						Family cu = new Family();
						table.setModel(cu.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "Расходы":
					
					try {
						Expenses cu = new Expenses();
						table.setModel(cu.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "Семья-расходы":
					try {
						Family_expenses da = new Family_expenses();
						table.setModel(da.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "Путешествие":
					try {
						Travel or = new Travel();
						table.setModel(or.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "Домашние расходы":
					try {
						Housing m = new Housing();
						table.setModel(m.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "Мелкие расходы":
					try {
						Small_Expenses d = new Small_Expenses();
						table.setModel(d.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "Образование":
					try {
						Education ac = new Education();
						table.setModel(ac.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "Семья-образование":
					try {
						Family_education da = new Family_education();
						table.setModel(da.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "Семья-домашнее":
					try {
						Family_housing da = new Family_housing();
						table.setModel(da.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "Семья-путешествие":
					try {
						Family_travel da = new Family_travel();
						table.setModel(da.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "Семья-мелкое":
					try {
						Family_small da = new Family_small();
						table.setModel(da.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "Тип":
					
					try {
						Tipe cu = new Tipe();
						table.setModel(cu.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				}
			}
		
	}
}
