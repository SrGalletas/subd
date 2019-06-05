package Entities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Expenses {
	private int id_ex;
	private String Expenses;
	private int money;
	private String date;
	private int id_type;

	
	public Expenses(int id_ex, String Expenses, int money, String date,int id_type){
		this.id_ex = id_ex;
		this.Expenses = Expenses;
		this.money = money;
		this.date = date;
		this.id_type = id_type;
	}
	
	public Expenses(String Expenses, int money, String date ,int id_type){
		this.Expenses = Expenses;
		this.money = money;
		this.date = date;
		this.id_type = id_type;

	}
	
	
	public Expenses(){
		
	}
	
	public int getId(){
		return id_ex;
	}
	
	public void setId(int id_ex){
		this.id_ex = id_ex;
	}
	
	public String getType(){
		return Expenses;
	}
	
	public void setType(String Expenses){
		this.Expenses = Expenses;
	}
	
	public int getMoney(){
		return money;
	}
	
	public void getMoney(int money){
		this.money = money;
	}
	
	public String getDate(){
		return date;
	}
	
	public void getDate(String date){
		this.date = date;
	}
	
	public int getIdfk(){
		return id_type;
	}
	
	public void setIdfk(int id_type){
		this.id_type = id_type;
	}
	
	public Vector<Object> setData(Connection conection) throws SQLException {
		Vector<Object> data = new Vector<Object>();
		data.add(id_ex);
		data.add(Expenses);
		data.add(money);
		data.add(date);
		data.add(id_type);
		return data;
	}

	public void addElement(String Expenses, int money, String date, int id_type, Connection connection) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("insert into expenses values(nextval('seq_expenses'),'" + Expenses + "', " + money + ", '" + date + "', " + id_type + ");");
	}

	public void removeElement(int id_ex, Connection connection) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("delete from expenses where id_ex = " + id_ex + ";");
	}

	public void refreshElement(int id_ex, String Expenses, int money, String date, int id_type, Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("update expenses set expenses = '" + Expenses + "', money = '" + money + "', date = '" + date + "', id_type = '" + id_type + "' where id_ex = " + id_ex + ";");
	}

	public DefaultTableModel TableModel(Connection connection) throws SQLException {
		Vector<Object> columnNames = null;
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		DefaultTableModel tableModel = new DefaultTableModel();
		columnNames = getTitles();
		data.add(columnNames);
		for (int i = 0; i <= getTable(connection).size() - 1; i++) {
			data.add(getTable(connection).get(i).setData(connection));
		}
		tableModel.setDataVector(data, columnNames);
		return tableModel;
	}

	public Vector<Object> getTitles() {
		Vector<Object> columnNames = new Vector<Object>();
		columnNames.add("id_ex");
		columnNames.add("Type");
		columnNames.add("Money");
		columnNames.add("Date");
		columnNames.add("id_type");
		return columnNames;
	}

	public ArrayList<Expenses> getTable(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from expenses;");
		ArrayList<Expenses> res = new ArrayList<>();
		while (rs.next()) {
			res.add(new Expenses((int) rs.getObject(1), rs.getObject(2).toString(), (int) rs.getObject(3), rs.getObject(4).toString(),(int) rs.getObject(5)));
		}
		return res;
	}
	
}
