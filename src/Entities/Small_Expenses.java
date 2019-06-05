package Entities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Small_Expenses {
	private int id_small;
	private String sm_ex;
	private int sm_m;
	
	public Small_Expenses(int id_small, String sm_ex, int sm_m){
		this.id_small = id_small;
		this.sm_ex = sm_ex;
		this.sm_m = sm_m;
	}
	
	public Small_Expenses(String sm_ex, int sm_m){
		this.sm_ex = sm_ex;
		this.sm_m = sm_m;
	}
	
	public Small_Expenses(){
		
	}
	
	public int getId(){
		return id_small;
	}
	
	public void setId(int id_small){
		this.id_small = id_small;
	}
	
	public String getType(){
		return sm_ex;
	}
	
	public void setType(String sm_ex){
		this.sm_ex = sm_ex;
	}
	
	public int getManufacturer(){
		return sm_m;
	}
	
	public void setManufacturer(int sm_m){
		this.sm_m = sm_m;
	}
	
	
	
	public Vector<Object> setData(Connection conection) throws SQLException {
		Vector<Object> data = new Vector<Object>();
		data.add(id_small);
		data.add(sm_ex);
		data.add(sm_m);
		return data;
	}

	public void addElement(String sm_ex, int sm_m, Connection connection) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("insert into small_expenses values(nextval('seq_small_expenses'),'" + sm_ex + "', " + sm_m + ");");
	}

	public void removeElement(int id_small, Connection connection) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("delete from small_expenses where id_small = " + id_small + ";");
	}

	public void refreshElement(int id_small, String sm_ex, int sm_m, Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("update small_expenses set small_expenses = '" + sm_ex + "', small_money = '" + sm_m + "' where id_small = " + id_small + ";");
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
		columnNames.add("id_small");
		columnNames.add("Type");
		columnNames.add("Money");
		return columnNames;
	}

	public ArrayList<Small_Expenses> getTable(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from small_expenses;");
		ArrayList<Small_Expenses> res = new ArrayList<>();
		while (rs.next()) {
			res.add(new Small_Expenses((int) rs.getObject(1), rs.getObject(2).toString(), (int)rs.getObject(3)));
		}
		return res;
	}


}
