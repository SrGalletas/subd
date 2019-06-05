package Entities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Housing {
	private int id_housing;
	private String h_exp;
	private int h_m;
	private String date;
	
	public Housing(int id_housing, String h_exp, int h_m, String date ){
		this.id_housing = id_housing;
		this.h_exp = h_exp;
		this.h_m = h_m;
		this.date = date;
	}
	
	public Housing(String h_exp, int h_m, String date ){
		this.h_exp = h_exp;
		this.h_m = h_m;
		this.date = date;
	}
	
	public Housing(){
		
	}
	
	public int getId(){
		return id_housing;
	}
	
	public void setId(int id_housing){
		this.id_housing = id_housing;
	}
	
	public String getFullName(){
		return h_exp;
	}
	
	public void setFullName(String h_exp){
		this.h_exp = h_exp;
	}
	
	public int getMoney(){
		return h_m;
	}
	
	public void setMoney(int h_m){
		this.h_m = h_m;
	}
	
	public String getTime(){
		return date;
	}
	
	public void setTime(String date){
		this.date = date;
	}
	
	public Vector<Object> setData(Connection conection) throws SQLException {
		Vector<Object> data = new Vector<Object>();
		data.add(id_housing);
		data.add(h_exp);
		data.add(h_m);
		data.add(date);
		return data;
	}

	public void addElement(String h_exp, int h_m, String date, Connection connection) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("insert into housing values(nextval('seq_housing'),'" + h_exp+ "', " + h_m + ", '" + date + "');");
	}

	public void removeElement(int id_housing, Connection connection) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("delete from housing where id_housing = " + id_housing + ";");
	}

	public void refreshElement(int id_housing, String h_exp, int h_m, String date, Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("update housing set hous_expenses = '" + h_exp + "', hous_money = " + h_m + ", date = '" + date + "' where id_housing = " + id_housing + ";");
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
		columnNames.add("id_housing");
		columnNames.add("Type");
		columnNames.add("Money");
		columnNames.add("Date");
		return columnNames;
	}

	public ArrayList<Housing> getTable(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from housing;");
		ArrayList<Housing> res = new ArrayList<>();
		while (rs.next()) {
			res.add(new Housing((int) rs.getObject(1), rs.getObject(2).toString(), (int) rs.getObject(3), rs.getObject(4).toString()));
		}
		return res;
	}

	
}
